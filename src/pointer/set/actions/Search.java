package pointer.set.actions;

import pointer.set.BodyType;
import pointer.set.Car;
import pointer.set.Color;
import pointer.set.components.TireType;

import java.util.*;

public class Search {
    private List<Integer> indexes = new ArrayList<>();
    private Set<? extends Car> cars = new HashSet<>();

    public List<Integer> getIndexes() {
        return indexes;
    }

    public void setCars(Set<? extends Car> cars) {
        this.cars = cars;
    }

    public void find(Color color) {
        Iterator<? extends Car> carIterator = cars.iterator();

        System.out.print("[");

        while (carIterator.hasNext()) {
            Car car = carIterator.next();

            if (car.getColor() == color) {
                System.out.print(car);

                if (carIterator.hasNext()) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println("]");
    }

    public void find(BodyType bodyType) {
        Iterator<? extends Car> carIterator = cars.iterator();
        int index = -1;

        System.out.print("[");

        while (carIterator.hasNext()) {
            Car car = carIterator.next();
            index++;

            if (car.getBodyType() == bodyType) {
                System.out.print(car);

                indexes.add(index);

                if (carIterator.hasNext()) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println("]");
    }

    public void find(float diameter) {
        Iterator<? extends Car> carIterator = cars.iterator();

        System.out.print("[");

        while (carIterator.hasNext()) {
            Car car = carIterator.next();

            if (car.getWheelsDiameter() == diameter) {
                System.out.print(car);

                if (carIterator.hasNext()) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println("]");
    }

    public void find(float diameter, Color color) {
        Iterator<? extends Car> carIterator = cars.iterator();

        System.out.print("[");
        while (carIterator.hasNext()) {
            Car car = carIterator.next();

            if (car.getWheelsDiameter() == diameter && car.getColor() == color) {
                System.out.print(car);

                if (carIterator.hasNext()) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println("]");
    }

    public void find(TireType type, float minDiameter, float maxDiameter) {
        Iterator<? extends Car> carIterator = cars.iterator();

        System.out.print("[");
        while (carIterator.hasNext()) {
            Car car = carIterator.next();

            if (car.getWheelsDiameter() >= minDiameter && car.getWheelsDiameter() <= maxDiameter
                    && car.getTireType() == type) {
                System.out.print(car);

                if (carIterator.hasNext()) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println("]");
    }
}