package controller;

import model.userModel.*;
import view.authenticationView.AdminPage;
import view.authenticationView.LoginPage;
import view.authenticationView.RegistrationPage;

import javax.swing.*;
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
        for (int i = 0; i < userDB.getAllUsers().size(); i++) {
            if (name.equals(userDB.getAllUsers().get(i).getName())
                    && password.equals(userDB.getAllUsers().get(i).getPassword())){
                JOptionPane.showMessageDialog(null, "This account already exist. Please try again.");
                new RegistrationPage();
                return;
            }
        }
        userDB.insertTable(name, password);
        loggedUser = new User(name, password);
        loggedUser.setUserStatus(UserStatus.LOGGED);
        JOptionPane.showMessageDialog(null, "Registration successful");
    }

    @Override
    public void login(String name, String password) {
        boolean isUserLogged = false;
        for (int i = 0; i < userDB.getAllUsers().size(); i++) {
            if (name.equals(userDB.getAllUsers().get(i).getName())
                    && password.equals(userDB.getAllUsers().get(i).getPassword())
                    && userDB.getAllUsers().get(i).getUserStatus() == UserStatus.LOGOUT
                    && !(name.equals(admin.getName()) && password.equals(admin.getPassword()))){
                loggedUser = userDB.getAllUsers().get(i);
                loggedUser.setUserStatus(UserStatus.LOGGED);
                JOptionPane.showMessageDialog(null, "Login successful");
                isUserLogged = true;
            }
        }

        if(!isUserLogged){
            JOptionPane.showMessageDialog(null, "Login failed. Please try again");
            new LoginPage();
        }
    }

    @Override
    public void loginAsAdmin(String name, String password) {
        boolean areAdminDataCorrect = false;
        if(name.equals(admin.getName()) && password.equals(admin.getPassword())){
            loggedUser = admin;
            loggedUser.setUserStatus(UserStatus.LOGGED);
            JOptionPane.showMessageDialog(null, "Login successful");
            areAdminDataCorrect = true;
        }

        if(!areAdminDataCorrect){
            JOptionPane.showMessageDialog(null, "Login failed. Please try again");
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
