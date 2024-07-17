package src.utils;

public class Manage {
    private int numInt;
    private double numDouble;

    public int getNumInt() {
        return numInt;
    }

    public double getNumDouble() {
        return numDouble;
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
