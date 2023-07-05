package model.userModel;

import model.ConnectionDB;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBImpl implements UserDB{
    private Statement statement;

    public UserDBImpl() {
        try {
            statement = ConnectionDB.connect().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTable(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            String sql = "INSERT INTO users (username, password) " +
                    "VALUES ('" + username + "'" + ", " + "'" + hashedPassword + "')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        Connection con;
        PreparedStatement p ;
        ResultSet rs ;
        con = ConnectionDB.connect();
        try {
            String sql = "SELECT * FROM users";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("username");
                String password= rs.getString("password");
                allUsers.add(new User(userName, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void removeUserProfile(String username){
        try {
            String sql = "DELETE FROM users " +
                    "WHERE userName = '" + username + "'";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
