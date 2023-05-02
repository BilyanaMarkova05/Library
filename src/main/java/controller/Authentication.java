package controller;

import model.userModel.User;

import java.util.List;

public interface Authentication {

    void registration(String name, String password);

    void login(String name, String password);

    void loginAsAdmin(String name, String password);

    void logout();

    List<User> getAllUsers();

    void removeUserProfile(String username);

}
