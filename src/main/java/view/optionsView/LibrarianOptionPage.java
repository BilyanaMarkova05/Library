package view.optionsView;
import controller.AuthenticationImpl;
import controller.BookController;
import view.authenticationView.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class LibrarianOptionPage extends BaseOptionPage implements ActionListener {
    private final JButton addBookButton;
    private final JButton removeBookButton;
    private final JButton bookedBooksButton;
    private final JLabel bookNameLabel;
    private final JTextField bookNameField;
    private final BookController bookController;

    public LibrarianOptionPage(BookController bookController){
        super(bookController);
        this.addBookButton = new JButton();
        this.removeBookButton = new JButton();
        this.bookedBooksButton = new JButton();
        this.bookNameLabel = new JLabel();
        this.bookNameField = new JTextField();
        this.bookController = bookController;
        setupComponents();
    }

    private void setupComponents() {
        setupAddBookButton();
        setupRemoveBookButton();
        setupBookedBooksButton();
        setupBookNameLabel();
        setupBookNameField();
        setupFrame();
    }


    private void setupFrame() {
        this.add(addBookButton);
        this.add(removeBookButton);
        this.add(bookedBooksButton);
        this.add(bookNameLabel);
        this.add(bookNameField);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Options");
    }

    private void setupBookNameField() {
        bookNameField.setBounds(150, this.getY()+25, 130, 20);
    }

    private void setupBookNameLabel() {
        bookNameLabel.setText("Book name: ");
        bookNameLabel.setBounds(80, this.getY()+15, 80, 40);
    }

    private void setupBookedBooksButton() {
        bookedBooksButton.setText("Booked books");
        bookedBooksButton.setBounds(145, this.getY()+120, 130, 20);
        bookedBooksButton.setFocusable(false);
        bookedBooksButton.addActionListener(this);
    }

    private void setupRemoveBookButton() {
        removeBookButton.setText("Remove book");
        removeBookButton.setBounds(145, this.getY()+90, 130, 20);
        removeBookButton.setFocusable(false);
        removeBookButton.addActionListener(this);
    }

    private void setupAddBookButton() {
        addBookButton.setText("Add book");
        addBookButton.setBounds(160, this.getY()+60, 100, 20);
        addBookButton.setFocusable(false);
        addBookButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(addBookButton)) {
            Scanner scanner = new Scanner(System.in);
            String genre = scanner.nextLine();
            bookController.addBook(bookNameField.getText(), "FREE", genre);
            navigateToLibrarianOptionPage();
        } else if (source.equals(removeBookButton)) {
            bookController.removeBook(bookNameField.getText());
            navigateToLibrarianOptionPage();
        } else if (source.equals(bookedBooksButton)) {
            navigateToBookedBooksPage();
        } else if(source.equals(this.getLogoutButton())){
            navigateToHomePage();
            AuthenticationImpl.getInstance().logout();
        } else if(source.equals(this.getReturnButton())){
            navigateToHomePage();
        }
    }

    private void navigateToHomePage() {
        this.dispose();
        HomePage.getInstance().setVisible(true);
    }

    private void navigateToBookedBooksPage() {
        this.dispose();
        new BookedBooksPage(bookController).setVisible(true);
    }

    private void navigateToLibrarianOptionPage() {
        this.dispose();
        new LibrarianOptionPage(bookController).setVisible(true);
    }
}
