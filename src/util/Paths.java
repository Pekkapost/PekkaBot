package util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Resolves project-relative paths against the location of this class's
// containing JAR (or compile output dir), not the process CWD. Mirrors
// DESIGN.md §1: anchor every path on the file's own location, not on
// wherever `java` happened to be invoked from.
//
// At runtime, getProtectionDomain().getCodeSource().getLocation() points at:
//   - the JAR file itself, when launched via `java -jar PekkaBot.jar`
//   - the compile output directory, when launched from an IDE
// Either way, its parent directory is the project root that contains
// data/ and libs/ at deployment time.
public final class Paths {
    private static final Logger logger = LoggerFactory.getLogger(Paths.class);
    private static final File PROJECT_ROOT = resolveProjectRoot();

    private Paths() {}

    public static File data(String relative) {
        return new File(new File(PROJECT_ROOT, "data"), relative);
    }

    // SQLite's JDBC connection string takes a string path, not a File.
    public static String dataPath(String relative) {
        return data(relative).getAbsolutePath();
    }

    private static File resolveProjectRoot() {
        try {
            URL location = Paths.class.getProtectionDomain().getCodeSource().getLocation();
            File codeSource = new File(location.toURI());
            // JAR file → parent is the project root.
            // Compile output dir (e.g. out/production/PekkaBot/) → walk up
            // until we find one that has data/ or libs/ alongside it.
            File candidate = codeSource.isFile() ? codeSource.getParentFile() : codeSource;
            for (int i = 0; i < 6 && candidate != null; i++) {
                if (new File(candidate, "data").isDirectory()
                        || new File(candidate, "libs").isDirectory()) {
                    return candidate;
                }
                candidate = candidate.getParentFile();
            }
            logger.warn("Could not locate project root from {}; falling back to CWD", location);
            return new File("").getAbsoluteFile();
        } catch (URISyntaxException e) {
            logger.error("Failed to resolve project root URI", e);
            return new File("").getAbsoluteFile();
        }
    }
}
