package pointer.set.components;

public class AbstractWheel {
    private float diameter;

    public AbstractWheel(float diameter) {
        if (diameter < 0) {
            throw new IllegalArgumentException("Diameter should be positive.");
        }

        this.diameter = diameter;
    }


    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float diameter) {
        if (diameter < 0) {
            throw new IllegalArgumentException("Diameter should be positive.");
        }

        this.diameter = diameter;
    }
}
