package pointer.set.components;

public enum TireType {
    SUMMER, WINTER, UNIVERSAL;

    public static TireType fromString(String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}