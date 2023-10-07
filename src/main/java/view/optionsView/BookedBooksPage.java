package view.optionsView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import view.BasePage;
import view.authenticationView.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BookedBooksPage extends BasePage implements ActionListener {
    private final JButton returnButton;
    private final JButton logoutButton;
    private final BookController bookController;
    private final List<JLabel> bookedBooks;
    private final Authentication authentication;
    private int y;

    public BookedBooksPage(BookController bookController){
        this.returnButton = new JButton();
        this.logoutButton = new JButton();
        this.bookController = bookController;
        this.bookedBooks = new ArrayList<>();
        this.authentication = AuthenticationImpl.getInstance();
        this.y = 40;
        setupComponents();
    }

    private void setupComponents() {
        setupBookedBooks();
        setupReturnButton();
        setupLogoutButton();
        setupFrame();
    }

    private void setupBookedBooks() {
        List<String> bookedBooks = bookController.getBookedBooks();
        for (int i = 0; i < bookedBooks.size(); i++) {
            this.bookedBooks.add(new JLabel());
            this.bookedBooks.get(i).setText(bookedBooks.get(i));
            this.bookedBooks.get(i).setBounds(140, y, 190, 60);
            this.add(this.bookedBooks.get(i));
            y+= 30;
        }
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

    private void setupFrame() {
        this.add(returnButton);
        this.add(logoutButton);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Booked books");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(returnButton)){
            navigateToAdminOptionPage();
        }else if (source.equals(logoutButton)){
            authentication.logout();
            navigateToAuthenticationPage();
        }
    }

    private void navigateToAuthenticationPage() {
        this.dispose();
        HomePage.getInstance().setVisible(true);
    }

    private void navigateToAdminOptionPage() {
        this.dispose();
        new AdminOptionPage(bookController).setVisible(true);
    }
}
