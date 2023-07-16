package model.userModel;

import java.util.List;

public interface UserDB {

    void insertTableUsers(String username, String password);

    void insertTableLibrarians(String name, String password);

    List<User> getAllUsers(String table);

    void removeUserProfile(String username, String tableName);
}
