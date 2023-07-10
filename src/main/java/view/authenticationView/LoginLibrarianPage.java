package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import controller.BookControllerImpl;
import model.userModel.UserStatus;
import view.optionsView.LibrarianOptionPage;
import view.optionsView.UserOptionPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginLibrarianPage extends JFrame implements ActionListener{
    private final JLabel nameLabel;
    private final JTextField nameField;
    private final JLabel passwordLabel;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton returnButton;
    private final Authentication authentication;
    private BookController bookController;

    public LoginLibrarianPage(){
        this.nameLabel = new JLabel();
        this.nameField = new JTextField();
        this.passwordLabel = new JLabel();
        this.passwordField = new JPasswordField();
        this.loginButton = new JButton();
        this.returnButton = new JButton();
        this.authentication = AuthenticationImpl.getInstance();
        setupComponents();
    }

    private void setupComponents() {
        setupNameLabel();
        setupNameField();
        setupPasswordLabel();
        setupPasswordField();
        setupLoginButton();
        setupReturnButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(nameLabel);
        this.add(nameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);
        this.add(returnButton);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Login");
        this.setBounds(550, 150, 420, 630);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupReturnButton() {
        returnButton.setText("return");
        returnButton.setBounds(10, 20, 90, 20);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupLoginButton() {
        loginButton.setText("login");
        loginButton.setBounds(100, 300, 200, 30);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
    }

    private void setupPasswordField() {
        passwordField.setBounds(160, 240, 150, 30);
    }

    private void setupPasswordLabel() {
        passwordLabel.setText("Password: ");
        passwordLabel.setBounds(90, 240, 80, 40);
    }

    private void setupNameField() {
        nameField.setBounds(160, 190, 150, 30);
    }

    private void setupNameLabel() {
        nameLabel.setText("Name: ");
        nameLabel.setBounds(90, 190, 80, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(returnButton)){
            navigateToAuthenticationPage();
        }
        if (source.equals(loginButton)) {
            String password = new String(passwordField.getPassword());
            authentication.loginAsLibrarian(nameField.getText(), password);
            this.dispose();

            if (AuthenticationImpl.getLoggedUser() == null
                    || AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED) {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again");
            } else if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED) {
                JOptionPane.showMessageDialog(null, "Login successful");
                bookController = new BookControllerImpl();
                navigateToLibrarianOptionPage();
            }
        }
    }

    private void navigateToAuthenticationPage() {
        this.dispose();
        AuthenticationPage.getInstance().setVisible(true);
    }

    private void navigateToLibrarianOptionPage(){
        this.dispose();
        new LibrarianOptionPage(bookController).setVisible(true);
    }
}
