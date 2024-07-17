package src.utils;

public enum Status {
    SUCCESS("Accomplished"),
    INVALID_INTEGER("Invalid Integer"),
    INVALID_DOUBLE("Invalid Double"),
    NO_INPUT("NO Input Provided"),
    INVALID_RANGE("Invalid range"),
    INVALID_CATEGORY("Invalid Category");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
