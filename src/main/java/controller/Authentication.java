package controller;

import model.userModel.User;

import java.util.List;

public interface Authentication {

    void register(String name, String password);

    void registerLibrarian(String name, String password);

    void login(String name, String password, String table);

    void loginAsAdmin(String name, String password);

    void logout();

    List<User> getAllUsers(String table);

    void removeProfile(String username, String tableName);


}
