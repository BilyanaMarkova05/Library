package controller;

import model.bookModel.Book;
import model.bookModel.BookDB;
import model.bookModel.BookDBImpl;
import model.bookModel.BookStatus;
import model.userModel.User;
import javax.swing.*;
import java.util.List;

public class BookControllerImpl implements BookController{
    private final BookDB bookDB;
    private final User loggedUser;
    private final Authentication authentication;

    public BookControllerImpl() {
        this.bookDB = new BookDBImpl();
        this.loggedUser = AuthenticationImpl.getLoggedUser();
        this.authentication = AuthenticationImpl.getInstance();
    }

    @Override
    public void updateBookTitle(String currentBookTitle, String newBookTitle) {
        bookDB.updateBookTitle(currentBookTitle, newBookTitle);
    }

    @Override
    public void updateBookAuthor(String bookTitle, String author) {
        bookDB.updateBookAuthor(bookTitle, author);
    }

    @Override
    public void updateBookGenre(String bookTitle, String genre) {
        bookDB.updateBookGenre(bookTitle, genre);
    }

    @Override
    public void updateBookNumber(Book book, int number) {
        bookDB.updateBookNumber(book, number);
    }

    public void rentBook(String bookName) {
        List<Book> allBooks = bookDB.getAllBooks();
        for (Book book : allBooks) {
            if (book.getName().equals(bookName) && book.getBookStatus() == BookStatus.BOOKED && book.getNumber() == 0){
                JOptionPane.showMessageDialog(null, bookName + " is already booked");
            }else if (book.getName().equals(bookName) &&
                    book.getBookStatus() == BookStatus.FREE && book.getNumber() != 0) {
                bookDB.updateBookNumber(book, book.getNumber() - 1);
                bookDB.insertIntoBookedBooksTable(loggedUser.getName(), bookName);
            }else if (book.getNumber() == 1){
                bookDB.updateBookStatus(bookName, "BOOKED");
            }
        }
    }

    public void returnBook(String bookName){
        for (Book bookedBook : bookDB.getBookedBooksByUser(loggedUser)) {
            if (bookName.equals(bookedBook.getName())) {
                for (int i = 0; i < bookDB.getAllBooks().size(); i++) {
                    if (bookDB.getAllBooks().get(i).getName().equals(bookName)) {
                        bookDB.updateBookNumber(bookedBook, bookedBook.getNumber() + 1);
                        bookDB.deleteFromBookedBooks(loggedUser.getName(), bookName);
                        if (bookedBook.getNumber() == 1){
                            bookDB.updateBookStatus(bookName, "FREE");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void addSearchedBook(String title) {
        for (Book b: bookDB.getAllBooks()) {
            if (b.getName().equals(title)){
                bookDB.insertIntoSearchedBooksTable(b);
            }
        }
    }

    @Override
    public void truncateSearchedBooks() {
        bookDB.truncateSearchedBooksTable();
    }

    @Override
    public List<Book> getSearchedBooks() {
        return bookDB.getSearchedBooks();
    }

    @Override
    public void removeSearchedBooks() {
        bookDB.removeSearchedBook();
    }

    @Override
    public void addBook(String bookName, String bookStatus, String genre, String author, int number) {
        for (Book book: bookDB.getAllBooks()) {
            if (book.getName().equals(bookName)){
                JOptionPane.showMessageDialog(null, bookName + " already exist.");
                return;
            }
        }
        bookDB.insertIntoBooksTable(bookName, bookStatus, genre, author, number);
    }

    @Override
    public void removeBook(String bookName) {
        for (Book book: bookDB.getAllBooks()) {
            if (book.getName().equals(bookName) && book.getBookStatus() == BookStatus.FREE){
                bookDB.removeBook(bookName);
            } else if(book.getName().equals(bookName) && book.getBookStatus() == BookStatus.BOOKED){
                JOptionPane.showMessageDialog(null, "This book is booked. You can't remove it");
            }
        }
    }

    public List<Book> getAllBooks(){
        return bookDB.getAllBooks();
    }

    @Override
    public List<Book> getBookedBooksByUser(User user) {
        return bookDB.getBookedBooksByUser(user);
    }

    @Override
    public List<String> getBookedBooks() {
        return bookDB.getBookedBooks();
    }

    @Override
    public boolean doesUserHaveBooks(String username) {
        boolean doesUserHaveBooks = false;
        for (User user :
                authentication.getAllUsers("users")) {
            if (user.getName().equals(username)) {
                if (!(getBookedBooksByUser(user).isEmpty())) {
                    doesUserHaveBooks = true;
                }
            }
        }
        return doesUserHaveBooks;
    }
}
