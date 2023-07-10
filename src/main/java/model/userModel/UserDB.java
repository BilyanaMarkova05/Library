package model.userModel;

import java.util.List;

public interface UserDB {

    void insertTableUsers(String username, String password);

    void insertTableLibrarians(String name, String password);

    List<User> getAllUsers();

    List<User> getAllLibrarians();

    void removeUserProfile(String username);
}
