package pointer.set;

import pointer.set.actions.Changer;
import pointer.set.actions.Search;
import pointer.set.components.TireType;

import java.util.*;

public class CarManager {

    Changer changer = new Changer();
    private LinkedHashSet<Car> cars = new LinkedHashSet<>();
    private Search search = new Search();

    CarManager() {
        search.setCars(cars);
    }

    public void setSearch(Search search) {
        this.search = search;
        this.search.setCars(cars);
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

    void addCarToList(Car car) {
        if (car == null) {
            return;
        }

        System.out.println(car + " is added.");
        cars.add(car);
    }

    LinkedHashSet<? extends Car> doSearch() {
        return cars;
    }

    void makeDoubleWheelsDiameter(boolean steeringWheelHasButtons) {
        changer.setSteeringWheelHasButtons(steeringWheelHasButtons);

        cars.iterator().forEachRemaining(c -> changer.makeDoubleWheelsDiameter(c));
    }

    void changeSteeringWheel(Color color, float diameter, boolean steeringWheelHasButtons) {
        changer.setColor(color);
        changer.setDiameter(diameter);
        changer.setSteeringWheelHasButtons(steeringWheelHasButtons);

        cars.iterator().forEachRemaining(c -> changer.changeSteeringWheel(c));
    }

    void changeTireType(Color color, float diameter, TireType tireType) {
        System.out.println("Changing tire type to " + tireType);

        changer.setColor(color);
        changer.setDiameter(diameter);
        changer.setTireType(tireType);

        cars.iterator().forEachRemaining(c -> changer.changeTireType(c));
    }

    public void changeCarsWithSmallDiameter(float diameter, Scanner scanner) {
        changer.setDiameter(diameter);
        changer.setScanner(scanner);
        changer.setCars(cars);

        cars.iterator().forEachRemaining(car -> changer.changeCarWithSmallDiameter(car));
    }

    public void changeWheels(float minDiameter, float maxDiameter, TireType tireType) {
        System.out.println("Changing wheels to " + tireType);

        changer.setMinDiameter(minDiameter);
        changer.setMaxDiameter(maxDiameter);
        changer.setTireType(tireType);

        cars.iterator().forEachRemaining(car -> changer.changeWheels(car));
    }

    public void remove(Color color) {
        if (cars.removeIf(car -> (car.getColor() == color))) {
            System.out.println("Cars are removed.");
        }
    }

    public void remove(float minDiameter, float maxDiameter) {
        boolean removed = cars.removeIf(car -> (car.getSteeringWheel().getDiameter() >= minDiameter &&
                car.getSteeringWheel().getDiameter() <= maxDiameter));

        if (removed) {
            System.out.println("Cars are removed.");
        }
    }

    public void remove(BodyType bodyType, float minDiameter, float maxDiameter) {
        boolean removed = cars.removeIf(car -> (car.getBodyType() == bodyType && car.getSteeringWheel().getDiameter() >= minDiameter &&
                car.getSteeringWheel().getDiameter() <= maxDiameter));

        if (removed) {
            System.out.println("Cars are removed.");
        }
    }

    public List<Integer> getIndexes() {
        return search.getIndexes();
    }

    public List<? extends Car> doSearch(String value){
        try {
            return search.find(BodyType.fromString(value));
        } catch (IllegalArgumentException bex) {
            try {
            return doSearch(Color.fromString(value));
            } catch (IllegalArgumentException cex) {
                throw new IllegalArgumentException(bex.getMessage() + "\nOR " + cex.getMessage(), bex);
            }
        }
    }

    public List<? extends Car> doSearch(Color color) {
        search.clearResults();
        search.setColor(color);

        cars.iterator().forEachRemaining(car -> search.findCarsWithColor(car));

        return search.getCars();
    }


    public List<? extends Car> doSearch(BodyType bodyType) {
        return search.find(bodyType);
    }

    public List<? extends Car> doSearch(Color color, Float diameter) {
        search.clearResults();
        search.setColor(color);
        search.setDiameter(diameter);

        cars.iterator().forEachRemaining(car -> search.findCarWithColorAndDiameter(car));

        return search.getCars();
    }

    public List<? extends Car> doSearch(TireType tire, Float minDiameter, Float maxDiameter) {
        return search.find(tire, minDiameter, maxDiameter);
    }
}