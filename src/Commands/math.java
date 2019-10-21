package Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class math{
    public static void callMe(MessageReceivedEvent event, String message) throws ScriptException{
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        event.getTextChannel().sendMessage("The answer is: `" + engine.eval(message).toString() + "`").queue();
    }
}
