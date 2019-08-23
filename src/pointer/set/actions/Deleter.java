package pointer.set.actions;

import pointer.set.BodyType;
import pointer.set.Car;
import pointer.set.Color;

import java.util.HashSet;
import java.util.Set;

public class Deleter {

    private Set<? extends Car> cars = new HashSet<>();

    public void setCars(Set<? extends Car> cars) {
        this.cars = cars;
    }

    public void remove(Color color) {
        boolean removed = cars.removeIf(car -> (car.getColor() == color));

        if (removed) {
            System.out.println("Cars are removed.");
        }
    }

    public void remove(BodyType type, float minDiameter, float maxDiameter) {
        boolean removed = cars.removeIf(car -> (car.getBodyType() == type && car.getSteeringWheel().getDiameter() >= minDiameter &&
                car.getSteeringWheel().getDiameter() <= maxDiameter));

        if (removed) {
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
}