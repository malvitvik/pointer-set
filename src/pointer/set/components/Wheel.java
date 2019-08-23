package pointer.set.components;

import java.util.Objects;

public class Wheel extends AbstractWheel {
    private float pressure;
    private TireType tireType;


    public Wheel(float diameter) {
        super(diameter);
    }

    public float getPressure() {
        return pressure;
    }

    public TireType getTireType() {
        return tireType;
    }

    public void setTireType(TireType tireType) {
        this.tireType = tireType;
    }

    public void pump() {
        pressure = getDiameter() <= 1.0 ? 2.1f : (getDiameter() <= 2.5) ? 2.7f : 3.5f;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wheel)) return false;
        Wheel wheel = (Wheel) o;
        return Float.compare(wheel.getPressure(), getPressure()) == 0 &&
                Float.compare(wheel.getDiameter(), getDiameter()) == 0 &&
                getTireType() == wheel.getTireType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPressure(), getTireType(), getDiameter());
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "type=" + tireType +
                ", pressure=" + pressure +
                ", diameter=" + getDiameter() +
                '}';
    }
}
