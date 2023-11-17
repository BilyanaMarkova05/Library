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

    public void rentBook(String bookName) {
        List<Book> allBooks = bookDB.getAllBooks();
        for (Book book : allBooks) {
            if (book.getName().equals(bookName) && book.getBookStatus() == BookStatus.BOOKED){
                JOptionPane.showMessageDialog(null, bookName + " is already booked");
            }else if (book.getName().equals(bookName) &&
                    book.getBookStatus() == BookStatus.FREE) {
                bookDB.updateBookStatus(bookName, "BOOKED");
                bookDB.insertBookedBooksTable(loggedUser.getName(), bookName);
            }
        }
    }

    public void returnBook(String bookName){
        for (Book bookedBook : bookDB.getBookedBooksByUser(loggedUser)) {
            if (bookName.equals(bookedBook.getName())) {
                for (int i = 0; i < bookDB.getAllBooks().size(); i++) {
                    if (bookDB.getAllBooks().get(i).getName().equals(bookName) &&
                            bookDB.getAllBooks().get(i).getBookStatus() == BookStatus.BOOKED) {
                        bookDB.updateBookStatus(bookName, "FREE");
                        bookDB.deleteFromBookedBooks(loggedUser.getName(), bookName);
                    }
                }
            }
        }
    }

    @Override
    public void addBook(String bookName, String bookStatus, String genre) {
        for (Book book: bookDB.getAllBooks()) {
            if (book.getName().equals(bookName)){
                JOptionPane.showMessageDialog(null, bookName + " already exist.");
                return;
            }
        }
        bookDB.insertBooksTable(bookName, bookStatus, genre);
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
