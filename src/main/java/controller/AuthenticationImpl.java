package controller;

import model.userModel.*;
import org.mindrot.jbcrypt.BCrypt;
import view.authenticationView.AdminPage;
import view.authenticationView.LoginPage;
import view.authenticationView.RegistrationPage;

import java.util.List;

public class AuthenticationImpl implements Authentication{
    private final Admin admin;
    private static User loggedUser;
    private final UserDB userDB;
    private static Authentication instance;

    public AuthenticationImpl() {
        this.admin = new Admin();
        loggedUser = new User();
        this.userDB =  new UserDBImpl();
    }

    public static Authentication getInstance(){
        if (instance == null){
            instance = new AuthenticationImpl();
        }
        return instance;
    }

    @Override
    public void registration(String name, String password) {
        for (User user : userDB.getAllUsers()) {
            boolean passwordMatch = BCrypt.checkpw(password, user.getPassword());
            if (name.equals(user.getName()) && passwordMatch){
                new RegistrationPage();
                return;
            }
        }
        userDB.insertTable(name, password);
        loggedUser = new User(name, password);
        loggedUser.setUserStatus(UserStatus.LOGGED);
    }

    @Override
    public void login(String name, String password) {
        boolean isUserLogged = false;
        for (User user : userDB.getAllUsers()) {
            boolean passwordMatch = BCrypt.checkpw(password, user.getPassword());
            if (name.equals(user.getName()) && passwordMatch
                    && !(name.equals(admin.getName()) && password.equals(admin.getPassword()))) {
                loggedUser = user;
                loggedUser.setUserStatus(UserStatus.LOGGED);
                isUserLogged = true;
            }
        }
        if(!isUserLogged){
            new LoginPage();
        }
    }

    @Override
    public void loginAsAdmin(String name, String password) {
        boolean areAdminDataCorrect = false;
        if(name.equals(admin.getName()) && password.equals(admin.getPassword())){
            loggedUser = admin;
            loggedUser.setUserStatus(UserStatus.LOGGED);
            areAdminDataCorrect = true;
        }

        if(!areAdminDataCorrect){
            new AdminPage();
        }
    }

    @Override
    public void logout() {
        loggedUser.setUserStatus(UserStatus.LOGOUT);
        loggedUser = null;
    }

    @Override
    public List<User> getAllUsers() {
        return userDB.getAllUsers();
    }

    @Override
    public void removeUserProfile(String username) {
        userDB.removeUserProfile(username);
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
}
