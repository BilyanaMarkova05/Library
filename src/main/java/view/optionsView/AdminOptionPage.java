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

public class AdminOptionPage extends BaseOptionPage implements ActionListener {
    private final JButton usersButton;
    private final JButton registerLibrarianButton;
    private final BookController bookController;
    private final Authentication authentication;

    public AdminOptionPage(BookController bookController){
        this.usersButton = new JButton();
        this.registerLibrarianButton = new JButton();
        this.bookController = bookController;
        this.authentication = AuthenticationImpl.getInstance();
        setupComponents();
    }

    public void setupComponents() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(this.getReturnButton())){
            navigateToPage(this, HomePage.getInstance());
        } else if (source.equals(this.getLogoutButton())){
            authentication.logout();
            navigateToPage(this, HomePage.getInstance());
        } else if (source.equals(usersButton)){
            navigateToPage(this, new UserPage(bookController));
        } else if (source.equals(registerLibrarianButton)){
            navigateToPage(this, new RegistrationLibrarianPage("Registration", "Sign in"));
        }
    }
}
