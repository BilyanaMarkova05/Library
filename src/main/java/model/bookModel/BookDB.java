package model.bookModel;

import model.userModel.User;

import java.util.List;

public interface BookDB {

    void updateBookStatus (String bookName, String bookStatus);

    void updateBookTitle (String currentBookTitle, String newBookTitle);

    void updateBookNumber(Book book, int number);

    List<Book> getAllBooks();

    List<String> getBookedBooksNamesByUser(User user);

    List<Book> getBookedBooksByUser(User user);

    List<String> getBookedBooks();

    void insertBookedBooksTable(String username, String bookName);

    void insertBooksTable(String bookName, String bookStatus, String genre, String author, int number);

    void deleteFromBookedBooks(String userName, String bookName);

    void removeBook(String bookName);
}
