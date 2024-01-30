package controller;

import model.userModel.*;
import org.mindrot.jbcrypt.BCrypt;
import view.authenticationView.*;
import view.optionsView.adminOptionsPages.AdminOptionPage;

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
    public void register(String name, String password) {
        for (User user : userDB.getAllUsers("users")) {
            boolean passwordMatch = BCrypt.checkpw(password, user.getPassword());
            if (name.equals(user.getName()) && passwordMatch){
                new RegistrationUserPage();
                return;
            }
        }
        userDB.insertTableUsers(name, password);
        loggedUser = new User(name, password);
        loggedUser.setUserStatus(UserStatus.LOGGED);
    }

    @Override
    public void registerLibrarian(String name, String password) {
        for (User user : userDB.getAllUsers("librarians")) {
            boolean passwordMatch = BCrypt.checkpw(password, user.getPassword());
            if (name.equals(user.getName()) && passwordMatch){
                new RegistrationLibrarianPage("Registration", "Sign in");
                return;
            }
        }
        userDB.insertTableLibrarians(name, password);
        new AdminOptionPage(new BookControllerImpl());
    }

    public void login(String name, String password, String table) {
        for (User user : userDB.getAllUsers(table)) {
            boolean passwordMatch = BCrypt.checkpw(password, user.getPassword());
            if (name.equals(user.getName()) && passwordMatch
                    && !(name.equals(admin.getName()) && password.equals(admin.getPassword()))) {
                loggedUser = user;
                loggedUser.setUserStatus(UserStatus.LOGGED);
            }
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
    public List<User> getAllUsers(String table) {
        return userDB.getAllUsers(table);
    }

    @Override
    public void removeProfile(String username, String tableName) {
        userDB.removeUserProfile(username, tableName);
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
}
