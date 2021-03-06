package pointer.set;

import pointer.set.components.*;

import java.util.Arrays;
import java.util.Objects;

public class Car {

    private final Body body;
    private String brand;
    private float totalDistance;
    private SteeringWheel steeringWheel = new SteeringWheel(1.0f, false);
    private Engine engine;
    private Wheel[] wheels;
    private Tank tank;

    public Car(String brand, Color carColor, BodyType type, Wheel... wheels) {
        this(brand, new Body(carColor, type), new Engine(2.0f, 120, 8.1f),
                new Tank(60), wheels);
    }

    public Car(String brand, Body body, Engine engine, Tank tank, Wheel... wheels) {
        this.brand = brand;
        this.body = body;
        this.engine = engine;
        this.tank = tank;
        this.wheels = wheels;
        pumpWheels();
    }

    public void setEngine(float volume, int power, float gasUsage) {
        this.engine = new Engine(volume, power, gasUsage);
    }

    public void setTank(int tank) {
        this.tank = new Tank(tank);
    }

    public Color getColor() {
        return body.getColor();
    }

    public BodyType getBodyType() {
        return body.getType();
    }

    public void changeWheel(int index, Wheel wheel) {
        System.out.println("Wheel " + index + " is changed to new one.");
        wheels[index] = wheel;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public void setSteeringWheel(SteeringWheel steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    public void start() {
        if (!engine.isOn()) {
            engine.ingine();
        }
    }

    public void turn(Direction direction) {
        steeringWheel.turn(direction);
    }

    public void clean() {
        body.clean();
    }

    private void pumpWheels() {
        for (Wheel wheel : wheels) {
            wheel.pump();
        }
    }

    public void changeWheelsDiameter(float multiply) {
        for (Wheel wheel : wheels) {
            wheel.setDiameter(multiply * wheel.getDiameter());
        }

    }

    public void drive(int km) {
        if (km <= 0) {
            System.out.println("You have set negative or zero distance.");
            return;
        }

        if (tank.isEmpty()) {
            System.out.println("The gas is run out. Please re-fuel the car.");
            return;
        }

        float distance = tank.getFuel() / engine.getGasUsage() * 100;

        if (km <= distance) {
            totalDistance += km;
            tank.setFuel(tank.getFuel() - engine.getGasUsage() * km / 100);
            System.out.println("Car is driven during " + km + ". Total distance is: " + totalDistance + " km.");
        } else {
            totalDistance += distance;
            tank.setFuel(0); // all fuel are used.
            System.out.println("You cannot drive full distance. Car is driven during " + distance + ". Total distance is: " + totalDistance + " km.");
        }
    }

    public void reFuel(int fuelVolume) {
        tank.reFuel(fuelVolume);
    }

    public void distance() {
        System.out.print("Car brand: " + brand +
                "\nCar color: " + body.getColor().name() +
                "\nTank is " + tank.getVolume() +
                "\nTotal distance: " + totalDistance +
                "\nPressure in wheels: ");

        for (int i = 0; i < (wheels.length - 1); i++) {
            System.out.print(("wheel " + i + " has pressure " + wheels[i].getPressure() + ", "));
        }

        System.out.println(("wheel " + (wheels.length - 1) + " has pressure " + wheels[wheels.length - 1].getPressure() + "."));
    }

    public void stop() {
        if (engine.isOn()) {
            engine.stop();
        }
    }

    public float getWheelsDiameter() {
        return wheels.length > 0 ? wheels[0].getDiameter() : 0;
    }

    public TireType getTireType() {
        return wheels[0].getTireType();
    }

    public void changeWheels(TireType tireType) {
        for (Wheel wheel : wheels) {
            wheel.setTireType(tireType);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Float.compare(car.totalDistance, totalDistance) == 0 &&
                brand.equals(car.brand) &&
                getSteeringWheel().equals(car.getSteeringWheel()) &&
                engine.equals(car.engine) &&
                Arrays.equals(wheels, car.wheels) &&
                body.equals(car.body) &&
                tank.equals(car.tank);
    }

    @Override
    public int hashCode() {
        int result = brand.hashCode() & getSteeringWheel().hashCode() & body.hashCode() & tank.hashCode();
        result = 31 * result + 13 * Objects.hash(totalDistance, engine) + Arrays.hashCode(wheels);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand +
                "\', " + body +
                ", steeringWheel=" + steeringWheel +
                ", wheels diameter=" + wheels[0].getDiameter() +
                "}";
    }
}