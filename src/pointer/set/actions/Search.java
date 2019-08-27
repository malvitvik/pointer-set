package pointer.set.actions;

import pointer.set.BodyType;
import pointer.set.Car;
import pointer.set.Color;
import pointer.set.components.TireType;

import java.util.*;

public class Search {
    private List<Integer> indexes = new ArrayList<>();
    private HashSet<Car> cars = new HashSet<>();

    private List<Car> result = new LinkedList<>();

    private Color color;
    private float diameter;

    public List<Car> getCars() {
        return result;
    }

    public void clearResults() {
        result = new LinkedList<>();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public void setCars(HashSet<Car> cars) {
        this.cars = cars;
    }

    public void findCarsWithColor(Car car) {
        if (car.getColor() == color) {
            result.add(car);
        }
    }

    public void findCarWithColorAndDiameter(Car car) {
        if (car.getWheelsDiameter() == diameter && car.getColor() == color) {
            result.add(car);
        }
    }

    public List<? extends Car> find(Color color) {
        Iterator<? extends Car> carIterator = cars.iterator();

        List<Car> cars = new LinkedList<>();

        while (carIterator.hasNext()) {
            Car car = carIterator.next();

            if (car.getColor() == color) {
                cars.add(car);
            }
        }

        return cars;
    }

    public List<? extends Car> find(BodyType bodyType) {
        Iterator<? extends Car> carIterator = cars.iterator();
        int index = -1;

        List<Car> cars = new LinkedList<>();

        while (carIterator.hasNext()) {
            Car car = carIterator.next();
            index++;

            if (car.getBodyType() == bodyType) {
                cars.add(car);

                indexes.add(index);
            }
        }

        return cars;
    }

    public List<? extends Car> find(float diameter) {
        Iterator<? extends Car> carIterator = cars.iterator();

        List<Car> list = new LinkedList<>();

        while (carIterator.hasNext()) {
            Car car = carIterator.next();

            if (car.getWheelsDiameter() == diameter) {
                list.add(car);
            }
        }

        return list;
    }

    public List<? extends Car> find(float diameter, Color color) {
        Iterator<? extends Car> carIterator = cars.iterator();

        List<Car> cars = new LinkedList<>();

        while (carIterator.hasNext()) {
            Car car = carIterator.next();

            if (car.getWheelsDiameter() == diameter && car.getColor() == color) {
                cars.add(car);
            }
        }

        return cars;
    }

    public List<? extends Car> find(TireType type, float minDiameter, float maxDiameter) {
        Iterator<? extends Car> carIterator = cars.iterator();

        List<Car> cars = new LinkedList<>();
        while (carIterator.hasNext()) {
            Car car = carIterator.next();

            if (car.getWheelsDiameter() >= minDiameter && car.getWheelsDiameter() <= maxDiameter
                    && car.getTireType() == type) {
                cars.add(car);
            }
        }

        return cars;
    }
}