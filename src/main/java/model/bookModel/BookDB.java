package model.bookModel;

import model.userModel.User;

import java.util.List;

public interface BookDB {

    void updateBookStatus (String bookName, String bookStatus);

    List<Book> getAllBooks();

    List<String> getBookedBooksFromUser(User user);

    List<String> getBookedBooks();

    void insertBookedBooksTable(String username, String bookName);

    void insertBooksTable(String bookName, String bookStatus);

    void deleteFromBookedBooks(String userName, String bookName);

    void removeBook(String bookName);
}
