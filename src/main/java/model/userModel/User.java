package model.userModel;

public class User {
    private String name;
    private String password;
    private UserStatus userStatus;

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.userStatus = UserStatus.LOGOUT;
    }

    public User(String name, String password, UserStatus userStatus){
        this.name = name;
        this.password = password;
        this.userStatus = userStatus;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
