
import model.ConnectionDB;
import view.authenticationView.AuthenticationPage;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        AuthenticationPage.getInstance();

//        try {
//            Statement statement = ConnectionDB.connect().createStatement();
//            String sql = "DELETE FROM librarians " +
//                    "WHERE name = '" + "pesho" + "'";
//            statement.executeUpdate(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
