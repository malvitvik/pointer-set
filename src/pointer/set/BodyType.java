package pointer.set;

import java.util.Arrays;

public enum BodyType {
    SEDAN, HATCHBACK, UNIVERSAL, JEEP, MINIVAN, VAN;

    public static BodyType fromString(String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new IllegalArgumentException("BodyType " + value + "' isn't allowed. Allowed types: "
                    + Arrays.toString(values()));
        }
    }

}
