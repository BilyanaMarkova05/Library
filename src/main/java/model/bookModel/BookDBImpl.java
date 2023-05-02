package model.bookModel;

import model.ConnectionDB;
import model.userModel.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDBImpl implements BookDB{
    private Statement statement;

    public BookDBImpl() {
        try {
            statement = ConnectionDB.connect().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBookStatus (String bookName, String bookStatus) {
        String SQL = "UPDATE books "
                + "SET bookStatus = ? "
                + "WHERE bookName = ?";

        try (Connection conn = ConnectionDB.connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {

            statement.setString(1, bookStatus);
            statement.setString(2, bookName);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();
        Connection con ;
        PreparedStatement p ;
        ResultSet rs ;
        con = ConnectionDB.connect();
        try {
            String sql = "SELECT * FROM books";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            while (rs.next()) {
                String bookName = rs.getString("bookName");
                String bookStatus = rs.getString("bookStatus");
                allBooks.add(new Book(bookName, validateBookStatus(bookStatus)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooks;
    }

    public List<String> getBookedBooksFromUser(User user) {
        List<String> bookedBooks = new ArrayList<>();
        try {
            ResultSet rs=statement.executeQuery("select * from bookedBooks where userName = '" + user.getName() + "'");
            while(rs.next()) {
                bookedBooks.add(rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bookedBooks;
    }

    @Override
    public List<String> getBookedBooks() {
        List<String> bookedBooks = new ArrayList<>();
        Connection con ;
        PreparedStatement p ;
        ResultSet rs ;
        con = ConnectionDB.connect();
        try {
            String sql = "SELECT * FROM bookedBooks";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String bookName = rs.getString("bookName");
                bookedBooks.add(username + " - " + bookName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedBooks;
    }

    private BookStatus validateBookStatus(String bookStatus){
        if (bookStatus.equals("BOOKED")){
            return BookStatus.BOOKED;
        }else {
            return BookStatus.FREE;
        }
    }

    public void insertBookedBooksTable(String username, String bookName) {
        try {
            String sql = "INSERT INTO bookedBooks (username, bookName) " +
                    "VALUES ('" + username + "'" + ", " + "'" + bookName + "')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertBooksTable(String bookName, String bookStatus) {
        try {
            String sql = "INSERT INTO books (bookName, bookStatus) " +
                    "VALUES ('" + bookName + "'" + ", " + "'" + bookStatus + "')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromBookedBooks(String userName, String bookName){
        try {
            String sql = "DELETE FROM bookedBooks " +
                    "WHERE userName = '" + userName + "' AND bookName = '" + bookName + "'";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromBookedBooks(String userName) {
        try {
            String sql = "DELETE FROM bookedBooks " +
                    "WHERE userName = '" + userName + "'";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeBook(String bookName) {
        try {
            String sql = "DELETE FROM books " +
                    "WHERE bookName = '" + bookName + "'";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
