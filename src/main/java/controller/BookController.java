package controller;

import model.bookModel.Book;
import model.userModel.User;

import java.util.List;

public interface BookController {

    void rentBook(String bookName);

    void returnBook(String bookName);

    void addBook(String bookName, String bookStatus);

    void removeBook(String bookName);

    List<Book> getAllBooks();

    List<String> getBookedBooksFromLoggedUser();

    List<String> getBookedBooksFromUser(User user);

    List<String> getBookedBooks();

    void deleteFromBookedBooks(String userName);
}
