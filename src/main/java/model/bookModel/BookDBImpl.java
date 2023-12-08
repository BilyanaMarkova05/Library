package model.bookModel;

import model.userModel.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static model.ConnectionDB.connect;

public class BookDBImpl implements BookDB{
    private Statement statement;

    public BookDBImpl() {
        try {
            statement = connect().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBookStatus (String bookName, String bookStatus) {
        String SQL = "UPDATE books "
                + "SET bookStatus = ? "
                + "WHERE bookName = ?";

        try (Connection conn = connect();
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
        con = connect();
        try {
            String sql = "SELECT * FROM books";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            while (rs.next()) {
                String bookName = rs.getString("bookName");
                String bookStatus = rs.getString("bookStatus");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                String number = rs.getString("number");
                allBooks.add(new Book(bookName, validateBookStatus(bookStatus),author, genre, Integer.parseInt(number)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooks;
    }

    public List<String> getBookedBooksNamesByUser(User user) {
        List<String> bookedBooksNames = new ArrayList<>();
        try {
            ResultSet rs=statement.executeQuery("select * from bookedBooks where userName = '" + user.getName() + "'");
            while(rs.next()) {
                bookedBooksNames.add(rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return bookedBooksNames;
    }

    public List<Book> getBookedBooksByUser(User user) {
        List<Book> bookedBooks = new ArrayList<>();
        for (int i = 0; i < getBookedBooksNamesByUser(user).size(); i++) {
            for (int j = 0; j < getAllBooks().size(); j++) {
                if (getBookedBooksNamesByUser(user).get(i).equals(getAllBooks().get(j).getName())) {
                    bookedBooks.add(new Book(getAllBooks().get(j).getName(), getAllBooks().get(j).getBookStatus(),
                            getAllBooks().get(j).getAuthor(), getAllBooks().get(j).getGenre(),
                            getAllBooks().get(j).getNumber()));
                }
            }
        }
        return bookedBooks;
    }

    @Override
    public List<String> getBookedBooks() {
        List<String> bookedBooks = new ArrayList<>();
        Connection con ;
        PreparedStatement p ;
        ResultSet rs ;
        con = connect();
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
    public void insertBooksTable(String bookName, String bookStatus, String genre) {
        try {
            String sql = "INSERT INTO books (bookName, bookStatus, genre) " +
                    "VALUES ('" + bookName + "', '" + bookStatus + "', '" + genre+ "')";
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
