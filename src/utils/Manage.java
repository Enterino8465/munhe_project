package src.utils;

public class Manage {
    private int num;

    public int getNum() {
        return num;
    }

    public Status setInt(String numStr){
        Status status = ManageUtils.validInteger(numStr);
        if (status.equals(Status.SUCCESS)){
            int num = Integer.parseInt(numStr);
            if (status.equals(Status.SUCCESS)){
                this.num = num;
            }
        }
        return status;
    }
    public Status setDouble(String numStr){
        Status status = ManageUtils.validDouble(numStr);
        if (status.equals(Status.SUCCESS)){
            int num = Integer.parseInt(numStr);
            if (status.equals(Status.SUCCESS)){
                this.num = num;
            }
        }
        return status;
    }
}
