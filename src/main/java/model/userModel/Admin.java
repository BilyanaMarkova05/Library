package model.userModel;

public class Admin extends User{
    public Admin() {
        super("admin", "admin123", UserStatus.LOGOUT);
    }
}
