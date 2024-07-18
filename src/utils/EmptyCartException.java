package src.utils;

public class EmptyCartException extends Exception{
    @Override
    public String toString() {
        return "The Cart Is Empty";
    }
}
