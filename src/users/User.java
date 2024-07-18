package src.users;

import src.utils.Status;

public class User {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public Status setUserName(String userName) {
        this.userName = userName;
        return Status.SUCCESS;
    }

    public String getPassword() {
        return password;
    }

    public Status setPassword(String password) {
        this.password = password;
        return Status.SUCCESS;
    }
    public Status validatePassword(String attempt) {
        if(this.password.equals(attempt)){
            return Status.SUCCESS;
        }
        return Status.WRONG_PASSWORD;
    }

}
