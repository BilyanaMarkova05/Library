package view.optionsView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import model.bookModel.Book;
import view.authenticationView.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LibrarianOptionPage extends JFrame implements ActionListener {
    private final JButton addBookButton;
    private final JButton removeBookButton;
    private final JButton bookedBooksButton;
    private final JButton logoutButton;
    private final JButton returnButton;
    private final JLabel bookNameLabel;
    private final JTextField bookNameField;
    private final BookController bookController;
    private final List<JLabel> allBooks;
    private final Authentication authentication;
    private int y;

    public LibrarianOptionPage(BookController bookController){
        this.addBookButton = new JButton();
        this.removeBookButton = new JButton();
        this.bookedBooksButton = new JButton();
        this.logoutButton = new JButton();
        this.returnButton = new JButton();
        this.bookNameLabel = new JLabel();
        this.bookNameField = new JTextField();
        this.bookController = bookController;
        this.allBooks = new ArrayList<>();
        this.authentication = AuthenticationImpl.getInstance();
        this.y = 40;
        setupComponents();
    }

    private void setupComponents() {
        setupAllBooks();
        setupAddBookButton();
        setupRemoveBookButton();
        setupBookedBooksButton();
        setupLogoutButton();
        setupReturnButton();
        setupBookNameLabel();
        setupBookNameField();
        setupFrame();
    }


    private void setupFrame() {
        this.add(addBookButton);
        this.add(removeBookButton);
        this.add(bookedBooksButton);
        this.add(logoutButton);
        this.add(returnButton);
        this.add(bookNameLabel);
        this.add(bookNameField);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Options");
        this.setBounds(550, 150, 420, 630);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupBookNameField() {
        bookNameField.setBounds(150, y+25, 130, 20);
    }

    private void setupBookNameLabel() {
        bookNameLabel.setText("Book name: ");
        bookNameLabel.setBounds(80, y+15, 80, 40);
    }

    private void setupReturnButton() {
        returnButton.setText("return");
        returnButton.setBounds(10, 20, 90, 20);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupLogoutButton() {
        logoutButton.setText("Logout");
        logoutButton.setBounds(300, 20, 90, 20);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
    }

    private void setupBookedBooksButton() {
        bookedBooksButton.setText("Booked books");
        bookedBooksButton.setBounds(145, y+120, 130, 20);
        bookedBooksButton.setFocusable(false);
        bookedBooksButton.addActionListener(this);
    }

    private void setupRemoveBookButton() {
        removeBookButton.setText("Remove book");
        removeBookButton.setBounds(145, y+90, 130, 20);
        removeBookButton.setFocusable(false);
        removeBookButton.addActionListener(this);
    }

    private void setupAddBookButton() {
        addBookButton.setText("Add book");
        addBookButton.setBounds(160, y+60, 100, 20);
        addBookButton.setFocusable(false);
        addBookButton.addActionListener(this);
    }

    private void setupAllBooks() {
        List<Book> allBooksDb = bookController.getAllBooks();
        for (int i = 0; i < allBooksDb.size(); i++) {
            allBooks.add(new JLabel());
            allBooks.get(i).setText(allBooksDb.get(i).getName() + " " + allBooksDb.get(i).getBookStatus());
            allBooks.get(i).setBounds(140, y, 190, 60);
            this.add(allBooks.get(i));
            y+= 30;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(returnButton)){
            navigateToAuthenticationPage();
        } else if (source.equals(logoutButton)){
            authentication.logout();
            navigateToAuthenticationPage();
        } else if (source.equals(addBookButton)){
            bookController.addBook(bookNameField.getText(), "FREE");
            navigateToLibrarianOptionPage();
        } else if (source.equals(removeBookButton)){
            bookController.removeBook(bookNameField.getText());
            navigateToLibrarianOptionPage();
        } else if (source.equals(bookedBooksButton)){
            navigateToBookedBooksPage();
        }
    }

    private void navigateToBookedBooksPage() {
        this.dispose();
        new BookedBooksPage(bookController).setVisible(true);
    }

    private void navigateToLibrarianOptionPage() {
        this.dispose();
        new LibrarianOptionPage(bookController).setVisible(true);
    }

    private void navigateToAuthenticationPage() {
        this.dispose();
        HomePage.getInstance().setVisible(true);
    }
}
