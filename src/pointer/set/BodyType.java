package pointer.set;

public enum BodyType {
    SEDAN, HATCHBACK, UNIVERSAL, JEEP, MINIVAN, VAN;

    public static BodyType fromString(String value) {
        BodyType[] bodyTypes = values();
        String valueUp = value.trim().toUpperCase();

        for (BodyType bodyType : bodyTypes) {
            if (bodyType.name().equals(valueUp)) {
                return bodyType;
            }
        }

        return null;
    }

}
