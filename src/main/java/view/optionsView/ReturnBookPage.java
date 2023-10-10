package view.optionsView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import view.BasePage;
import view.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookPage extends BasePage implements ActionListener {
    private final List<JLabel> bookedBooks;
    private final JTextField bookNameField;
    private final JLabel bookNameLabel;
    private final JButton returnBookButton;
    private BookController bookController;
    private final JButton returnButton;
    private final JButton logoutButton;
    private final Authentication authentication;
    private final JButton deleteProfileButton;

    public ReturnBookPage(BookController bookController){
        this.bookNameLabel = new JLabel();
        this.bookedBooks = new ArrayList<>();
        this.bookNameField = new JTextField();
        this.returnBookButton = new JButton();
        this.bookController = bookController;
        this.returnButton = new JButton();
        this.logoutButton = new JButton();
        this.authentication = AuthenticationImpl.getInstance();
        this.deleteProfileButton = new JButton();
        setupComponents();
    }

    private void setupComponents() {
        setupReturnButton();
        setupBookedBooks();
        setupBookNameLabel();
        setupBookNameTextField();
        setupReturnBookButton();
        setupLogoutButton();
        setupDeleteProfileButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(returnBookButton);
        this.add(logoutButton);
        this.add(returnButton);
        this.add(bookNameLabel);
        this.add(bookNameField);
        this.add(deleteProfileButton);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Your books");
    }

    private void setupDeleteProfileButton() {
        deleteProfileButton.setText("Delete profile");
        deleteProfileButton.setBounds(270, 20, 120, 20);
        deleteProfileButton.setFocusable(false);
        deleteProfileButton.addActionListener(this);
    }

    private void setupLogoutButton() {
        logoutButton.setText("Logout");
        logoutButton.setBounds(300, 50, 90, 20);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
    }

    private void setupReturnBookButton() {
        returnBookButton.setText("Return book");
        returnBookButton.setBounds(150, 350, 130, 20);
        returnBookButton.setFocusable(false);
        returnBookButton.addActionListener(this);
    }

    private void setupBookNameTextField() {
        bookNameField.setBounds(150, 310, 130, 20);
    }

    private void setupBookNameLabel() {
        bookNameLabel.setText("Book name: ");
        bookNameLabel.setBounds(80, 300, 80, 40);
    }

    private void setupBookedBooks() {
        List<String> bookedBooksDb = bookController.getBookedBooksFromLoggedUser();
        int y = 100;
        for (int i = 0; i < bookedBooksDb.size(); i++) {
            bookedBooks.add(new JLabel());
            bookedBooks.get(i).setText(bookedBooksDb.get(i));
            bookedBooks.get(i).setBounds(160, y, 150, 60);
            this.add(bookedBooks.get(i));
            y+= 30;
        }
    }

    private void setupReturnButton() {
        returnButton.setText("return");
        returnButton.setBounds(10, 20, 90, 20);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(returnButton)){
            navigateToOptionPage();
        }else if (source.equals(logoutButton)){
            authentication.logout();
            bookController = null;
            navigateToAuthenticationPage();
        } else if (source.equals(returnBookButton)){
            bookController.returnBook(bookNameField.getText());
            this.dispose();
            new ReturnBookPage(bookController);
        }else if (source.equals(deleteProfileButton)){
            if (bookController.doesUserHaveBooks(AuthenticationImpl.getLoggedUser().getName())){
                JOptionPane.showMessageDialog(null,"This user have booked books. " +
                        "You cannot delete this account before the books are returned.");
            }else {
                authentication.removeProfile(AuthenticationImpl.getLoggedUser().getName(), "users");
                this.dispose();
                navigateToAuthenticationPage();
            }
        }
    }

    private void navigateToOptionPage() {
        this.dispose();
        new UserOptionPage(bookController).setVisible(true);
    }

    private void navigateToAuthenticationPage() {
        this.dispose();
        HomePage.getInstance().setVisible(true);
    }
}
