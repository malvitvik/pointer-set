package pointer.set.actions;

import pointer.set.BodyType;
import pointer.set.Car;
import pointer.set.Color;
import pointer.set.components.SteeringWheel;
import pointer.set.components.TireType;
import pointer.set.components.Wheel;

import java.util.Scanner;
import java.util.Set;

public class Changer {

    private Color color;
    private float diameter;
    private boolean steeringWheelHasButtons;
    private TireType tireType;
    private float minDiameter;
    private float maxDiameter;
    private Scanner scanner;
    private Set<Car> cars;

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public void setMinDiameter(float minDiameter) {
        this.minDiameter = minDiameter;
    }

    public void setMaxDiameter(float maxDiameter) {
        this.maxDiameter = maxDiameter;
    }

    public void setTireType(TireType tireType) {
        this.tireType = tireType;
    }

    public void setSteeringWheelHasButtons(boolean steeringWheelHasButtons) {
        this.steeringWheelHasButtons = steeringWheelHasButtons;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public void changeSteeringWheel(Car car) {
        if (car.getColor() == color) {
            car.setSteeringWheel(new SteeringWheel(diameter, steeringWheelHasButtons));
            System.out.println("Steering Wheel is changed for car: " + car);
        }
    }

    public void changeTireType(Car car) {
        if (car.getColor() == color && car.getWheelsDiameter() == diameter) {
            car.changeWheels(tireType);
            System.out.println("Wheels are changed for " + car);
        }
    }

    public void changeWheels(Car car) {
        if (car.getWheelsDiameter() >= minDiameter && car.getWheelsDiameter() <= maxDiameter) {
            car.changeWheels(tireType);
            System.out.println("Wheels are changed for " + car);
        }
    }

    public void makeDoubleWheelsDiameter(Car car) {
        if (car.getSteeringWheel().hasButtons() == steeringWheelHasButtons) {
            car.changeWheelsDiameter(2);
            System.out.println("Double diameter is made for " + car);
        }
    }

    public void changeCarWithSmallDiameter(Car car) {
        if (car.getWheelsDiameter() < diameter) {
            System.out.println(car + " has diameter smaller than " + diameter + ". Add new one:");

            cars.remove(car);
            float d;
            cars.add(new Car(scanner.next(), Color.fromString(scanner.next()),
                    BodyType.fromString(scanner.next()), new Wheel(d = scanner.nextFloat()),
                    new Wheel(d), new Wheel(d), new Wheel(d)));
        }
    }
}
