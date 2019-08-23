package pointer.set.components;

import java.util.Objects;

public class SteeringWheel extends AbstractWheel {
    private final boolean buttons;
    private float position;

    public SteeringWheel(float diameter, boolean hasButtons) {
        super(diameter);
        this.buttons = hasButtons;
    }

    public void turn(Direction direction) {
        position += direction.getDegree();
        System.out.println("Steering wheel position: " + position);
    }

    public boolean hasButtons() {
        return buttons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SteeringWheel)) return false;
        SteeringWheel that = (SteeringWheel) o;
        return Float.compare(that.position, position) == 0 &&
                Float.compare(that.getDiameter(), getDiameter()) == 0 &&
                buttons == that.buttons;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, buttons, getDiameter());
    }

    @Override
    public String toString() {
        return "{" +
                "buttons=" + buttons +
                ", diameter=" + getDiameter() +
                '}';
    }
}
