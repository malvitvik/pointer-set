package pointer.set.components;

import java.util.Arrays;

public enum TireType {
    SUMMER, WINTER, UNIVERSAL;

    public static TireType fromString(String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new IllegalArgumentException("TireType " + value + "' isn't allowed. Allowed types: "
                    + Arrays.toString(values()));
        }
    }
}