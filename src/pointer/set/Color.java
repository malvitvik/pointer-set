package pointer.set;

public enum Color {
    BLACK, WHITE, RED, GREEN, YELLOW, BLUE, ORANGE, PURPLE;

    public static Color fromString(String value) {
        Color[] colors = values();
        String valuesUp = value.trim().toUpperCase();

        for (Color c : colors) {
            if (c.name().equals(valuesUp)) {
                return c;
            }
        }

        return null;
    }
}
