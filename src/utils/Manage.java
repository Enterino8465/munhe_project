package src.utils;

public class Manage {
    private int numInt;
    private double numDouble;
    private String str;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public String getStr() {
        return str;
    }

    public int getNumInt() {
        return numInt;
    }

    public double getNumDouble() {
        return numDouble;
    }
    public Status setCategory(String categoryStr){
        Status status = ManageUtils.validCategory(categoryStr);
        if (status.equals(Status.SUCCESS)){
            this.category = Category.valueOf(categoryStr.toUpperCase());
        }
        return status;
    }

    public Status setString(String str){
        Status status = ManageUtils.validString(str);
        if (status.equals(Status.SUCCESS)){
            this.str = str.trim();
        }
        return status;
    }

    public Status setInt(String numStr) {
        Status status = ManageUtils.validInteger(numStr);
        if (status.equals(Status.SUCCESS)) {
            this.numInt = Integer.parseInt(numStr);
        }
        return status;
    }

    public Status setDouble(String numStr) {
        Status status = ManageUtils.validDouble(numStr);
        if (status.equals(Status.SUCCESS)) {
            this.numDouble = Double.parseDouble(numStr);
        }
        return status;
    }
}
