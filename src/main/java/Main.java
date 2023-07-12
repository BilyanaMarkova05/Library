
import view.authenticationView.HomePage;

public class Main {

    public static void main(String[] args) {
        HomePage.getInstance();

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
