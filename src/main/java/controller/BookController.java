package controller;

import model.bookModel.Book;
import model.userModel.User;

import java.util.List;

public interface BookController {

    void updateBookTitle (String currentBookTitle, String newBookTitle);
    void updateBookAuthor (String bookTitle, String author);
    void updateBookGenre (String bookTitle, String genre);
    void updateBookNumber(Book book, int number);
    void rentBook(String bookName);

    void returnBook(String bookName);

    void addSearchedBook (String title);

    void truncateSearchedBooks();
    List<Book> getSearchedBooks();
    void removeSearchedBooks();

    void addBook(String bookName, String bookStatus, String genre,String author, int number);

    void removeBook(String bookName);

    List<Book> getAllBooks();

    List<Book> getBookedBooksByUser(User user);

    List<String> getBookedBooks();

    boolean doesUserHaveBooks(String username);
}
