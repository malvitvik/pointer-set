package pointer.set;

import pointer.set.actions.Changer;
import pointer.set.actions.Deleter;
import pointer.set.actions.Search;
import pointer.set.components.TireType;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class CarManager {

    private static final String WITH = "with";
    private static final String WITHOUT = "without";

    private LinkedHashSet<Car> cars = new LinkedHashSet<>();
    private Search search = new Search();
    private Changer changer = new Changer();
    private Deleter remover = new Deleter();

    CarManager() {
        search.setCars(cars);
        changer.setCars(cars);
        remover.setCars(cars);
    }

    public void setSearch(Search search) {
        this.search = search;
        this.search.setCars(cars);
    }

    public void setChanger(Changer changer) {
        this.changer = changer;
        this.changer.setCars(cars);
    }

    public void setRemover(Deleter remover) {
        this.remover = remover;
        this.remover.setCars(cars);
    }

    void help() {

        String stringBuilder = "Type 'add car brand, color, type and wheel diameter.\n" + "Colors: " + Arrays.toString(Color.values()) + "\n" +
                "Car types: " + Arrays.toString(BodyType.values()) + "\n" +
                "Type 'show' without parameters OR with body type OR color OR diameter OR diameter and color\n" +
                "show indexes - displays car indexes, shown by 'show body type'" +
                "Type 'update' with:\n" +
                "- 'with'/without': Make double diameter for cars 'with'/'without' buttons.\n" +
                "- body color AND true/false AND float: Change steering wheel to wheel with/without buttons " +
                "(boolean) and with new diameter (floet) for cars with some color.\n" +
                "- diameter: change cars with diameter smaller than entered." +
                "\n\n'rm' color OR 'rm' body type min-diameter max-diameter of steering wheel\n" +
                "Type 'help' to show this\nType 'done' for finish.";
        System.out.println(stringBuilder);
    }

    void addCarToList(String[] params) {
        Car car = changer.buildNewCar(params[0], params[1], params[2], params[3]);

        if (car == null) {
            return;
        }

        System.out.println(car + " is added.");
        cars.add(car);
    }

    void doSearch(String[] params) throws NumberFormatException {
        switch (params.length) {
            case 0:
                System.out.println(cars);
                break;
            case 1:
                searchByOneParameter(params[0]);
                break;
            case 2:
                searchByTwoParameters(params[0], params[1]);
                break;
            case 3:
                searchByTreeParameters(params[0], params[1], params[2]);
                break;
            default:
                System.out.println("Wrong parameters count.");
                break;
        }
    }

    void update(String[] params) throws NumberFormatException {
        String param = params[0];

        if (WITH.equals(param) || WITHOUT.equals(param)) {
            changer.makeDoubleWheelsDiameter(WITH.equals(param));
            return;
        }

        Color color = Color.fromString(param);

        if (color != null) {
            TireType tireType = TireType.fromString(params[2]);

            if (tireType != null) {
                changer.changeTireType(color, Float.parseFloat(params[1]), tireType);
                return;
            }

            changer.changeSteeringWheel(color, Float.parseFloat(params[2]),
                    Boolean.parseBoolean(params[1]));
            return;
        }

        color = Color.fromString(params[2]);
        BodyType bodyType = BodyType.fromString(params[3]);

        if (color != null && bodyType != null) {
            changer.changeCarsWithSmallDiameter(Float.parseFloat(param), params);
        }

        try {
            changer.changeWheels(Float.parseFloat(param), Float.parseFloat(params[1]),
                    TireType.fromString(params[2]));
        } catch (NumberFormatException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tire type is wong. Use: " +
                    Arrays.toString(TireType.values()), e);
        }

        System.out.println("Parameter '" + param + "' isn't allowed. Allowed 'with'/'without', one of colors: "
                + Arrays.toString(Color.values()));
    }

    void remove(String[] params) throws IllegalArgumentException {

        switch (params.length) {
            case 1:
                Color color = Color.fromString(params[0]);

                if (color == null) {
                    throw new IllegalArgumentException("Parameter '" + params[0] +
                            "' isn't allowed. Allowed colors: " + Arrays.toString(Color.values()));
                }

                remover.remove(color);
                break;

            case 2:
                remover.remove(Float.parseFloat(params[0]), Float.parseFloat(params[1]));
                break;

            case 3:
                BodyType bodyType = BodyType.fromString(params[0]);

                if (bodyType == null) {
                    throw new IllegalArgumentException("Parameter '" + params[0] +
                            "' isn't allowed. Allowed body types: " + Arrays.toString(BodyType.values()));
                }

                remover.remove(bodyType, Float.parseFloat(params[1]), Float.parseFloat(params[2]));
                break;
        }
    }

    private void searchByOneParameter(String parameter) throws NumberFormatException {
        if (parameter == null || "".equals(parameter)) {
            System.out.println("Wrong parameters count. Expected one parameter.");
            return;
        }

        if ("indexes".equals(parameter)) {
            System.out.println(search.getIndexes());
            return;
        }

        Color color = Color.fromString(parameter);

        if (color != null) {
            search.find(color);
            return;
        }

        BodyType bodyType = BodyType.fromString(parameter);

        if (bodyType != null) {
            search.find(bodyType);
            return;
        }

        search.find(Float.parseFloat(parameter));
    }

    private void searchByTwoParameters(String color, String diameter) throws IllegalArgumentException {
        Color c = Color.fromString(color);

        if (c == null) {
            throw new IllegalArgumentException("Color '" + color + "' isn't allowed. Allowed colors: "
                    + Arrays.toString(Color.values()));
        }

        search.find(Float.parseFloat(diameter), c);
    }

    private void searchByTreeParameters(String tire, String minDiameter, String maxDiameter) throws IllegalArgumentException {
        try {
            search.find(TireType.valueOf(tire.toUpperCase()), Float.parseFloat(minDiameter),
                    Float.parseFloat(maxDiameter));
        } catch (NumberFormatException ex) {
            throw ex;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Tire type '" + tire + " is wong. Use: " + Arrays.toString(TireType.values()));
        }
    }
}