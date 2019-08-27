package pointer.set;

import pointer.set.actions.Command;
import pointer.set.components.TireType;
import pointer.set.components.Wheel;

import java.util.Scanner;

public class Main {

    private static final String WITH = "with";
    private static final String WITHOUT = "without";

    public static void main(String[] args) {
        CarManager manager = new CarManager();
        Scanner scanner = new Scanner(System.in);

        manager.help();
        Command command = Command.fromString(scanner.next());

        while (command != Command.DONE) {
            try {
                switch (command) {
                    case CREATE:
                        float diameter;
                        manager.addCarToList(new Car(scanner.next(), Color.fromString(scanner.next()),
                                BodyType.fromString(scanner.next()), new Wheel(diameter = scanner.nextFloat()),
                                new Wheel(diameter), new Wheel(diameter), new Wheel(diameter)));
                        break;

                    case READ:
                        if (scanner.hasNext()) {
                            String next = scanner.next();

                            if ("indexes".equals(next)) {
                                System.out.println(manager.getIndexes());
                            }

                            if (scanner.hasNextFloat()) {
                                diameter = scanner.nextFloat();

                                if (scanner.hasNextFloat()) {
                                    System.out.println(manager.doSearch(TireType.fromString(scanner.next()),
                                            scanner.nextFloat(), scanner.nextFloat()));
                                    break;
                                }

                                System.out.println(manager.doSearch(Color.fromString(next),
                                        diameter));
                                break;
                            }

                            System.out.println(manager.doSearch(next));
                            break;
                        }

                        System.out.println(manager.doSearch());
                        break;

                    case UPDATE:

                        if (scanner.hasNextFloat()) {
                            diameter = scanner.nextFloat();

                            if (scanner.hasNextFloat()) {
                                manager.changeWheels(diameter, scanner.nextFloat(),
                                        TireType.fromString(scanner.next()));
                                break;
                            }

                            manager.changeCarsWithSmallDiameter(diameter, scanner);
                            break;
                        }

                        String next = scanner.next();

                        if (WITH.equals(next) || WITHOUT.equals(next)) {
                            manager.makeDoubleWheelsDiameter(WITH.equals(next));
                            break;
                        }

                        Color color = Color.fromString(next);

                        if (scanner.hasNextFloat()) {
                            diameter = scanner.nextFloat();

                            if (scanner.hasNextBoolean()) {
                                manager.changeSteeringWheel(color, diameter, scanner.nextBoolean());
                                break;
                            }

                            manager.changeTireType(color, diameter, TireType.fromString(scanner.next()));
                            break;
                        }

                        break;

                    case DELETE:
                        if (scanner.hasNextFloat()) {
                            manager.remove(scanner.nextFloat(), scanner.nextFloat());
                            break;
                        }

                        if (scanner.hasNext()) {
                            next = scanner.next();

                            if (scanner.hasNextFloat()) {
                                manager.remove(BodyType.fromString(scanner.next()), scanner.nextFloat(), scanner.nextFloat());
                                break;
                            }

                            manager.remove(Color.fromString(next));
                            break;
                        }
                        System.out.println("Wrong parameters.");
                        break;

                    case HELP:
                        manager.help();
                        break;
                }
            } catch (NumberFormatException nex) {
                System.out.println("Wheel diameter should be float");
            } catch (IllegalArgumentException iex) {
                System.out.println(iex.getMessage());
            } finally {
                command = Command.fromString(scanner.next());
            }
        }
        scanner.close();
    }
}