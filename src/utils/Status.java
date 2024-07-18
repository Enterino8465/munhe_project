package src.utils;

public enum Status {
    SUCCESS("Accomplished"),
    WRONG_PASSWORD("Wrong Password"),
    NAME_TAKEN("Username Already Exists"),
    NO_BUYER("No Buyers Added Yet"),
    NO_SELLER("No Sellers Added Yet"),
    NO_PRODUCT("No Product FOUND"),
    No_ORDER_HISTORY("You Didnt Ordered Yet"),
    INVALID_INTEGER("Invalid Integer"),
    INVALID_DOUBLE("Invalid Double"),
    NO_INPUT("NO Input Provided"),
    INVALID_RANGE("Invalid range"),
    INVALID_CATEGORY("Invalid Category"),
    EMPTY_CART("The Cart Is Empty"),
    NEGATIVE_PRICE("You Trying To Set Negative Price");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
