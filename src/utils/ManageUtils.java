package src.utils;

public class ManageUtils {
    public static Status validInteger(String num){
        if (num.isEmpty()){
            return Status.NO_INPUT;
        }
        try {
            Integer.parseInt(num);
        }catch (NumberFormatException e){
            return Status.INVALID_INTEGER;
        }
        return Status.SUCCESS;
    }
    public static Status validDouble(String num){
        if (num.isEmpty()){
            return Status.NO_INPUT;
        }
        try {
            Double.parseDouble(num);
        }catch (NumberFormatException e){
            return Status.INVALID_DOUBLE;
        }
        return Status.SUCCESS;
    }

    public static Status validRange(int num) {
        if (num > 0 && num < 100){
            return Status.SUCCESS;
        }
        return Status.INVALID_RANGE;
    }
}
