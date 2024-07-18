package src.utils;

public class ManageUtils {
    public static Status validString(String str){
        if(str.isEmpty()){
            return Status.NO_INPUT;
        }
        return Status.SUCCESS;
    }
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

    public static Status validateRange(int num, int maxVal) {
        if (num >= 0 && num <= maxVal){
            return Status.SUCCESS;
        }
        return Status.INVALID_RANGE;
    }
}
