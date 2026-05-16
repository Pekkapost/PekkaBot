package util;

import framework.command.Command;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reflection-based command discovery.
 *
 * Walks every class under the {@code commands} package (in the running
 * JAR or the compile-output directory), instantiates concrete subclasses
 * of {@link framework.command.Command}, and returns them sorted by name.
 *
 * Adding a new command is a single-file affair: drop a class under
 * {@code src/commands/<feature>/} that extends {@code Command} and
 * provides a no-arg constructor. No registration line anywhere is needed —
 * this loader picks it up on next startup.
 *
 * Per-class instantiation failures are logged and swallowed so one broken
 * command can't prevent the rest of the bot from starting.
 */
public final class CommandLoader {
    private static final Logger logger = LoggerFactory.getLogger(CommandLoader.class);
    private static final String COMMANDS_PACKAGE = "commands";

    private CommandLoader() {}

    public static List<Command> discover() {
        List<String> classNames = scanForClassNames();
        List<Command> commands = new ArrayList<>();
        for (String name : classNames) {
            try {
                Class<?> cls = Class.forName(name);
                if (!Command.class.isAssignableFrom(cls)) continue;
                if (cls == Command.class) continue;
                if (Modifier.isAbstract(cls.getModifiers())) continue;
                Command cmd = (Command) cls.getDeclaredConstructor().newInstance();
                commands.add(cmd);
            } catch (Throwable t) {
                // Swallow per-class failures so one broken command doesn't
                // prevent the rest of the bot from starting up.
                logger.error("Failed to load command {}", name, t);
            }
        }
        commands.sort(Comparator.comparing(c -> c.name.toLowerCase()));
        return commands;
    }

    private static List<String> scanForClassNames() {
        try {
            URL location = CommandLoader.class.getProtectionDomain().getCodeSource().getLocation();
            File source = new File(location.toURI());
            return source.isFile() ? scanJar(source) : scanDirectory(source);
        } catch (URISyntaxException | IOException e) {
            logger.error("Failed to scan classpath for commands", e);
            return List.of();
        }
    }

    private static List<String> scanJar(File jar) throws IOException {
        List<String> names = new ArrayList<>();
        String prefix = COMMANDS_PACKAGE + "/";
        try (JarFile jf = new JarFile(jar)) {
            Enumeration<JarEntry> entries = jf.entries();
            while (entries.hasMoreElements()) {
                String entry = entries.nextElement().getName();
                if (entry.startsWith(prefix) && entry.endsWith(".class") && !entry.contains("$")) {
                    String trimmed = entry.substring(0, entry.length() - ".class".length());
                    names.add(trimmed.replace('/', '.'));
                }
            }
        }
        return names;
    }

    private static List<String> scanDirectory(File root) {
        List<String> names = new ArrayList<>();
        File commandsRoot = new File(root, COMMANDS_PACKAGE);
        if (commandsRoot.isDirectory()) {
            walk(commandsRoot, COMMANDS_PACKAGE, names);
        }
        return names;
    }

    private static void walk(File dir, String packagePrefix, List<String> names) {
        File[] entries = dir.listFiles();
        if (entries == null) return;
        for (File f : entries) {
            if (f.isDirectory()) {
                walk(f, packagePrefix + "." + f.getName(), names);
            } else if (f.getName().endsWith(".class") && !f.getName().contains("$")) {
                String stem = f.getName().substring(0, f.getName().length() - ".class".length());
                names.add(packagePrefix + "." + stem);
            }
        }
    }
}
