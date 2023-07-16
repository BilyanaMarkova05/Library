import view.authenticationView.HomePage;


public class Main {

    public static void main(String[] args) {
        HomePage.getInstance();

//        try {
//            Statement statement = ConnectionDB.connect().createStatement();
//            String sql = "ALTER TABLE users " +
//                    "RENAME COLUMN username TO name";
//            statement.executeUpdate(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
