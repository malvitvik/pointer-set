package pointer.set;

import java.util.Arrays;

public enum Color {
    BLACK, WHITE, RED, GREEN, YELLOW, BLUE, ORANGE, PURPLE;

    public static Color fromString(String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Color " + value + "' isn't allowed. Allowed colors: "
                    + Arrays.toString(values()));
        }
    }
}
