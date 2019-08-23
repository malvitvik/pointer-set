package pointer.set.actions;

public enum Command {
    CREATE("add"), READ("show"), UPDATE("update"), DELETE("rm"), DONE("done"), HELP("help");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public static Command fromString(String value) {
        Command[] commands = values();
        String valueUp = value.trim().toUpperCase();

        for (Command c : commands) {
            if (c.name().equals(valueUp) || c.command.equals(value)) {
                return c;
            }
        }

        return null;
    }
}
