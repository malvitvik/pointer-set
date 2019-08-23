package pointer.set.actions;

import java.io.InputStream;
import java.util.Scanner;

public class ParameterizedCommand {
    private Scanner scanner;
    private Command command;
    private String[] params;

    public ParameterizedCommand(InputStream source) {
        scanner = new Scanner(source);
    }

    public void next() {
        String next = scanner.next();
        this.command = Command.fromString(next);

        while (command == null) {
            System.out.println("Wrong command: " + next);
            scanner.nextLine();

            next = scanner.next();
            this.command = Command.fromString(next);
        }

        this.params = scanner.nextLine().trim().split(" ");

        if (params[0].equals("")) {
            params = new String[0];
        }
    }

    public Command getCommand() {
        return command;
    }

    public String[] getParams() {
        return params;
    }

    public void close() {
        scanner.close();
    }
}
