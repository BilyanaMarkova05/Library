package view.optionsView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import view.HomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentBookPage extends BaseOptionPage implements ActionListener {
    private final JLabel bookNameLabel;
    private final JTextField bookNameField;
    private final JButton rentButton;
    private final Authentication authentication;
    private final JButton deleteProfileButton;
    private final BookController bookController;
    public RentBookPage(BookController bookController) {
        super(bookController);
        this.bookNameLabel = new JLabel();
        this.bookNameField = new JTextField();
        this.rentButton = new JButton();
        this.authentication = AuthenticationImpl.getInstance();
        this.bookController = bookController;
        this.deleteProfileButton = new JButton();
        setupComponents();
    }

    private void setupComponents() {
        setupAllBooks();
        setupBookNameLabel();
        setupBookNameField();
        setupRentButton();
        setupDeleteProfileButton();
    }

    private void setupDeleteProfileButton() {
        deleteProfileButton.setText("Delete profile");
        deleteProfileButton.setBounds(1550, 45, 120, 20);
        deleteProfileButton.setFocusable(false);
        deleteProfileButton.addActionListener(this);
        this.add(deleteProfileButton);
    }

    private void setupRentButton() {
        rentButton.setText("Rent");
        rentButton.setBounds(700, 350, 250, 200);
        rentButton.setFocusable(false);
        rentButton.addActionListener(this);
        this.add(rentButton);
    }

    private void setupBookNameField() {
        bookNameField.setBounds(150, this.getY()+25, 130, 20);
        this.add(bookNameField);
    }

    private void setupBookNameLabel() {
        bookNameLabel.setText("Book name: ");
        bookNameLabel.setBounds(80, this.getY()+15, 80, 40);
        this.add(bookNameLabel);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(rentButton)) {
            navigateToPage(this, new RentBookPage(bookController));
            bookController.rentBook(bookNameField.getText());
            navigateToPage(this, new UserOptionPage(bookController));
        } else if (source.equals(deleteProfileButton)) {
            if (bookController.doesUserHaveBooks(AuthenticationImpl.getLoggedUser().getName())) {
                JOptionPane.showMessageDialog(null, "This user have booked books. " +
                        "You cannot delete this account before the books are returned.");
            } else {
                authentication.removeProfile(AuthenticationImpl.getLoggedUser().getName(), "users");
                navigateToPage(this, HomePage.getInstance());
            }
        } else if (source.equals(this.getLogoutButton())) {
            authentication.logout();
            navigateToPage(this, HomePage.getInstance());
        }
    }
}
