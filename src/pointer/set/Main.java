package pointer.set;

import pointer.set.actions.Command;
import pointer.set.actions.ParameterizedCommand;

public class Main {

    public static void main(String[] args) {
        CarManager manager = new CarManager();
        ParameterizedCommand command = new ParameterizedCommand(System.in);

        manager.help();
        command.next();

        while (command.getCommand() != Command.DONE) {
            try {
                switch (command.getCommand()) {
                    case CREATE:
                        manager.addCarToList(command.getParams());
                        break;

                    case READ:
                        manager.doSearch(command.getParams());
                        break;

                    case UPDATE:
                        manager.update(command.getParams());
                        break;

                    case DELETE:
                        manager.remove(command.getParams());
                        break;

                    case HELP:
                        manager.help();
                        break;
                }
            } catch (NumberFormatException nex) {
                System.out.println("Wheel diameter should be float");

            } catch (IllegalArgumentException iaex) {
                System.out.println(iaex.getMessage());

            } finally {
                command.next();
            }
        }
        command.close();
    }
}