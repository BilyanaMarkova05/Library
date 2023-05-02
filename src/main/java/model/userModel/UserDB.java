package model.userModel;

import java.util.List;

public interface UserDB {

    void insertTable(String username, String password);

    List<User> getAllUsers();

    void removeUserProfile(String username);
}
