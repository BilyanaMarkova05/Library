package controller;

import model.bookModel.Book;
import model.bookModel.BookDB;
import model.bookModel.BookDBImpl;
import model.bookModel.BookStatus;
import model.userModel.User;
import model.userModel.UserDB;
import model.userModel.UserDBImpl;


import javax.swing.*;
import java.util.List;

public class BookControllerImpl implements BookController{
    private final BookDB bookDB;
    private final User loggedUser;
    private final UserDB userDB;
    private final Authentication authentication;

    public BookControllerImpl() {
        this.bookDB = new BookDBImpl();
        this.loggedUser = AuthenticationImpl.getLoggedUser();
        this.userDB = new UserDBImpl();
        this.authentication = AuthenticationImpl.getInstance();
    }

    public void rentBook(String bookName) {
        List<Book> allBooks = bookDB.getAllBooks();
        boolean doesBookExist = false;
        for (Book book : allBooks) {
            if (book.getName().equals(bookName) &&
                    book.getBookStatus() == BookStatus.FREE) {
                bookDB.updateBookStatus(bookName, "BOOKED");
                bookDB.insertBookedBooksTable(loggedUser.getName(), bookName);
                JOptionPane.showMessageDialog(null, bookName + " is rented");
                doesBookExist = true;
            }
        }

        if(!doesBookExist){
            JOptionPane.showMessageDialog(null, bookName + " does not exist. Please try again.");
        }
    }

    public void returnBook(String bookName){
        boolean doesBookExist = false;
        for (String bookedBook : bookDB.getBookedBooksFromUser(loggedUser)) {
            if (bookName.equals(bookedBook)) {
                for (int i = 0; i < bookDB.getAllBooks().size(); i++) {
                    if (bookDB.getAllBooks().get(i).getName().equals(bookName) &&
                            bookDB.getAllBooks().get(i).getBookStatus() == BookStatus.BOOKED) {
                        bookDB.updateBookStatus(bookName, "FREE");
                        bookDB.deleteFromBookedBooks(loggedUser.getName(), bookName);
                        JOptionPane.showMessageDialog(null, bookName + " is returned");
                        doesBookExist = true;
                    }
                }
            }
        }

        if (!doesBookExist){
            JOptionPane.showMessageDialog(null, bookName + " does not exist. Please try again.");
        }
    }

    @Override
    public void addBook(String bookName, String bookStatus) {
        for (Book book: bookDB.getAllBooks()) {
            if (book.getName().equals(bookName)){
                JOptionPane.showMessageDialog(null, bookName + " already exist.");
                return;
            }
        }
        bookDB.insertBooksTable(bookName, bookStatus);
    }

    @Override
    public void removeBook(String bookName) {
        bookDB.removeBook(bookName);
    }

    public List<Book> getAllBooks(){
        return bookDB.getAllBooks();
    }

    public List<String> getBookedBooksFromLoggedUser(){
        return bookDB.getBookedBooksFromUser(loggedUser);
    }

    @Override
    public List<String> getBookedBooksFromUser(User user) {
        return bookDB.getBookedBooksFromUser(user);
    }

    @Override
    public List<String> getBookedBooks() {
        return bookDB.getBookedBooks();
    }

    @Override
    public boolean doesUserHaveBooks(String username) {
        boolean doesUserHaveBooks = false;
        for (User user :
                authentication.getAllUsers()) {
            if (user.getName().equals(username)) {
                if (!(getBookedBooksFromUser(user).isEmpty())) {
                    doesUserHaveBooks = true;
                }
            }
        }
        return doesUserHaveBooks;
    }
}
