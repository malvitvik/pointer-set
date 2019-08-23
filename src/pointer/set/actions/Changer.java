package pointer.set.actions;

import pointer.set.BodyType;
import pointer.set.Car;
import pointer.set.Color;
import pointer.set.components.SteeringWheel;
import pointer.set.components.TireType;
import pointer.set.components.Wheel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Changer {

    private Set<Car> cars = new HashSet<>();

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public void makeDoubleWheelsDiameter(boolean steeringWheelHasButtons) {
        Iterator<? extends Car> carIterator = cars.iterator();

        carIterator.forEachRemaining(c -> {
            if (c.getSteeringWheel().hasButtons() == steeringWheelHasButtons) {
                c.changeWheelsDiameter(2);
                System.out.println("Double diameter is made for " + c);
            }
        });
    }

    public void changeSteeringWheel(Color color, float diameter, boolean hasButtons) {
        Iterator<? extends Car> carIterator = cars.iterator();

        carIterator.forEachRemaining(c -> {
            if (c.getColor() == color) {
                c.setSteeringWheel(new SteeringWheel(diameter, hasButtons));
                System.out.println("Steering Wheel is changed for car: " + c);
            }
        });
    }

    public void changeCarsWithSmallDiameter(float diameter, String[] params) {
        Iterator<Car> carIterator = cars.iterator();

        while (carIterator.hasNext()) {
            Car car = carIterator.next();

            if (car.getWheelsDiameter() >= diameter) {
                continue;
            }

            System.out.println(car + " has diameter smaller than " + diameter + ". Set new one:");
            Car newCar = null;

            while (newCar == null) {
                newCar = buildNewCar(params[1], params[2], params[3], params[4]);
            }

            cars.remove(car);
            cars.add(newCar);
        }
    }

    public void changeWheels(float minDiameter, float maxDiameter, TireType tireType) {
        Iterator<? extends Car> carIterator = cars.iterator();

        System.out.println("Changing wheels to " + tireType);

        carIterator.forEachRemaining(c -> {
            if (c.getWheelsDiameter() >= minDiameter && c.getWheelsDiameter() <= maxDiameter) {
                c.changeWheels(tireType);
                System.out.println("Wheels are changed for " + c);
            }
        });
    }

    public void changeTireType(Color color, float diameter, TireType tireType) {
        Iterator<? extends Car> carIterator = cars.iterator();

        System.out.println("Changing tire type to " + tireType);

        carIterator.forEachRemaining(c -> {
            if (c.getColor() == color && c.getWheelsDiameter() == diameter) {
                c.changeWheels(tireType);
                System.out.println("Wheels are changed for " + c);
            }
        });
    }

    public Car buildNewCar(String brand, String color, String type, String diameter) throws IllegalArgumentException {
        Color color1 = Color.fromString(color);

        if (color1 == null) {
            throw new IllegalArgumentException("Color '" + color + "' isn't allowed. Allowed colors: "
                    + Arrays.toString(Color.values()));
        }

        BodyType bodyType = BodyType.fromString(type);

        if (bodyType == null) {
            throw new IllegalArgumentException("Body type '" + type + "' isn't allowed. Allowed colors: "
                    + Arrays.toString(BodyType.values()));
        }

        float d;

        try {
            d = Float.parseFloat(diameter);
        } catch (NumberFormatException nex) {
            throw new IllegalArgumentException("Wheel diameter should be float", nex);
        }

        return new Car(brand, color1, bodyType,
                new Wheel(d), new Wheel(d), new Wheel(d), new Wheel(d));
    }
}