package pointer.set.components;

import pointer.set.BodyType;
import pointer.set.Color;

import java.util.Objects;

public class Body {
    private final BodyType type;
    private Color color;

    public Body(Color color, BodyType type) {
        setColor(color);
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BodyType getType() {
        return type;
    }

    public void clean() {
        System.out.println("Car body is clean");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Body)) return false;
        Body body = (Body) o;
        return getColor() == body.getColor() &&
                getType() == body.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getType());
    }

    @Override
    public String toString() {
        return "Body type=" + type +
                ", color=" + color;
    }
}
