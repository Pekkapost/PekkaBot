package framework.command;

public abstract class Command {
    public String name = "";
    public String help = "";
    public String[] aliases = new String[0];
    public String arguments = "";
    public boolean ownerCommand = false;
    public boolean hidden = false;

    protected abstract void execute(CommandEvent event);
}
