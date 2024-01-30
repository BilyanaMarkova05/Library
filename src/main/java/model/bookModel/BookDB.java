package model.bookModel;

import model.userModel.User;

import java.util.List;

public interface BookDB {

    void updateBookStatus (String bookName, String bookStatus);

    void updateBookTitle (String currentBookTitle, String newBookTitle);

    void updateBookAuthor(String bookTitle, String author);

    void updateBookGenre(String bookTitle, String genre);

    void updateBookNumber(Book book, int number);
    void insertIntoSearchedBooksTable(Book book);
    void truncateSearchedBooksTable();
    List<Book> getSearchedBooks();

    List<Book> getAllBooks();

    List<String> getBookedBooksNamesByUser(User user);

    List<Book> getBookedBooksByUser(User user);

    List<String> getBookedBooks();

    void insertIntoBookedBooksTable(String username, String bookName);

    void insertIntoBooksTable(String bookName, String bookStatus, String genre, String author, int number);

    void deleteFromBookedBooks(String userName, String bookName);

    void removeBook(String bookName);

    void removeSearchedBook();
}
