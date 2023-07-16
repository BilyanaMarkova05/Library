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

    public void insertTableUsers(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            String sql = "INSERT INTO users (username, password) " +
                    "VALUES ('" + username + "'" + ", " + "'" + hashedPassword + "')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTableLibrarians(String name, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            String sql = "INSERT INTO librarians (name, password) " +
                    "VALUES ('" + name + "'" + ", " + "'" + hashedPassword + "')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(String table) {
        List<User> allUsers = new ArrayList<>();
        Connection con;
        PreparedStatement p ;
        ResultSet rs ;
        con = ConnectionDB.connect();
        try {
            String sql = "SELECT * FROM " + table;
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("name");
                String password= rs.getString("password");
                allUsers.add(new User(userName, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void removeUserProfile(String username, String tableName){
        try {
            String sql = "DELETE FROM "+ tableName +
                    " WHERE name = '" + username + "'";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
