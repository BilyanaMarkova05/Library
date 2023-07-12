package view.optionsView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import model.bookModel.Book;

import model.userModel.User;
import view.authenticationView.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class UserOptionPage extends JFrame implements ActionListener {
    private final JButton rentButton;
    private final JButton yourBooksButton;
    private final JButton logoutButton;
    private final JButton returnButton;
    private final JLabel bookNameLabel;
    private final JTextField bookNameField;
    private final BookController bookController;
    private final List<JLabel> allBooks;
    private final Authentication authentication;
    private int y;
    private final JButton deleteProfileButton;

    public UserOptionPage(BookController bookController){
        this.rentButton = new JButton();
        this.yourBooksButton = new JButton();
        this.logoutButton = new JButton();
        this.returnButton = new JButton();
        this.bookNameLabel = new JLabel();
        this.bookNameField = new JTextField();
        this.bookController = bookController;
        this.allBooks = new ArrayList<>();
        this.authentication = AuthenticationImpl.getInstance();
        this.y = 40;
        this.deleteProfileButton = new JButton();
        setupComponents();

    }

    private void setupComponents() {
        setupAllBooks();
        setupRentButton();
        setupYourBooksButton();
        setupLogoutButton();
        setupReturnButton();
        setupBookNameLabel();
        setupBookNameField();
        setupDeleteProfileButton();
        setupFrame();
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

    private void setupFrame() {
        this.add(rentButton);
        this.add(yourBooksButton);
        this.add(logoutButton);
        this.add(returnButton);
        this.add(bookNameLabel);
        this.add(bookNameField);
        this.add(deleteProfileButton);
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
        logoutButton.setBounds(300, 50, 90, 20);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
    }

    private void setupYourBooksButton() {
        yourBooksButton.setText("Your books");
        yourBooksButton.setBounds(160, y+90, 100, 20);
        yourBooksButton.setFocusable(false);
        yourBooksButton.addActionListener(this);
    }

    private void setupRentButton() {
        rentButton.setText("Rent");
        rentButton.setBounds(160, y+60, 100, 20);
        rentButton.setFocusable(false);
        rentButton.addActionListener(this);
    }

    private void setupDeleteProfileButton() {
        deleteProfileButton.setText("Delete profile");
        deleteProfileButton.setBounds(270, 20, 120, 20);
        deleteProfileButton.setFocusable(false);
        deleteProfileButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(returnButton)){
            navigateToAuthenticationPage();
        } else if (source.equals(logoutButton)){
           authentication.logout();
           navigateToAuthenticationPage();
        } else if (source.equals(rentButton)){
            bookController.rentBook(bookNameField.getText());
            navigateToUserOptionPage();
        } else if (source.equals(yourBooksButton)) {
            navigateToReturnPage();
        } else if (source.equals(deleteProfileButton)){
            if (doesUserHaveBooks()){
                JOptionPane.showMessageDialog(null,"This user have booked books. " +
                        "You cannot delete this account before the books are returned.");
            }else {
                authentication.removeUserProfile(AuthenticationImpl.getLoggedUser().getName());
                navigateToAuthenticationPage();
            }
        }
    }

    private boolean doesUserHaveBooks(){
        boolean doesUserHaveBooks = false;
        for (User user:
                authentication.getAllUsers()) {
            if (user.getName().equals(AuthenticationImpl.getLoggedUser().getName())){
                if (!(bookController.getBookedBooksFromUser(user).isEmpty())){
                    doesUserHaveBooks = true;
                }
            }
        }
        return doesUserHaveBooks;
    }

    private void navigateToUserOptionPage() {
        this.dispose();
        new UserOptionPage(bookController);
    }

    private void navigateToAuthenticationPage() {
        this.dispose();
        HomePage.getInstance().setVisible(true);
    }

    private void navigateToReturnPage() {
        this.dispose();
        new ReturnBookPage(bookController).setVisible(true);
    }
}
