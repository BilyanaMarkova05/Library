package view.optionsView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import view.BasePage;
import view.HomePage;
import view.authenticationView.RegistrationLibrarianPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminOptionPage extends BasePage implements ActionListener {
    private final JButton logoutButton;
    private final JButton returnButton;
    private final JButton usersButton;
    private final JButton registerLibrarianButton;
    private final BookController bookController;
    private final Authentication authentication;

    public AdminOptionPage(BookController bookController){
        this.logoutButton = new JButton();
        this.returnButton = new JButton();
        this.usersButton = new JButton();
        this.registerLibrarianButton = new JButton();
        this.bookController = bookController;
        this.authentication = AuthenticationImpl.getInstance();
        setupComponents();
    }

    private void setupComponents() {
        setupLogoutButton();
        setupReturnButton();
        setupUsersButton();
        setupRegisterLibrarianButton();
        setupFrame();
    }

    private void setupRegisterLibrarianButton() {
        registerLibrarianButton.setText("register librarian");
        registerLibrarianButton.setBounds(110, 200, 190, 30);
        registerLibrarianButton.setFocusable(false);
        registerLibrarianButton.addActionListener(this);
    }

    private void setupFrame() {
        this.add(logoutButton);
        this.add(returnButton);
        this.add(usersButton);
        this.add(registerLibrarianButton);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Options");
    }

    private void setupUsersButton() {
        usersButton.setText("Users");
        usersButton.setBounds(110, 240, 190, 30);
        usersButton.setFocusable(false);
        usersButton.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(returnButton)){
            navigateToAuthenticationPage();
        } else if (source.equals(logoutButton)){
            authentication.logout();
            navigateToAuthenticationPage();
        } else if (source.equals(usersButton)){
            navigateToUserPage();
        } else if (source.equals(registerLibrarianButton)){
            navigateToRegistrationLibrarianPage();
        }
    }

    private void navigateToRegistrationLibrarianPage() {
        this.dispose();
        new RegistrationLibrarianPage("Registration", "Sign in");
    }

    private void navigateToUserPage() {
        this.dispose();
        new UserPage(bookController).setVisible(true);
    }

    private void navigateToAuthenticationPage() {
        this.dispose();
        HomePage.getInstance().setVisible(true);
    }
}
