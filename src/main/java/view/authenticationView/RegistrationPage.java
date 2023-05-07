package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import controller.BookControllerImpl;
import model.userModel.UserStatus;
import view.optionsView.UserOptionPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationPage extends JFrame implements ActionListener {
    private final JLabel nameLabel;
    private final JTextField nameField;
    private final JLabel passwordLabel;
    private final JTextField passwordField;
    private final JButton registrationButton;
    private final JButton returnButton;
    private final Authentication authentication;
    private BookController bookController;

    public RegistrationPage() {
        this.nameLabel = new JLabel();
        this.nameField = new JTextField();
        this.passwordLabel = new JLabel();
        this.passwordField = new JTextField();
        this.registrationButton = new JButton();
        this.returnButton = new JButton();
        this.authentication = AuthenticationImpl.getInstance();
        setupComponents();
    }

    private void setupComponents() {
        setupNameLabel();
        setupNameField();
        setupPasswordLabel();
        setupPasswordField();
        setupRegistrationButton();
        setupReturnButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(nameLabel);
        this.add(nameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(registrationButton);
        this.add(returnButton);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Registration");
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

    private void setupRegistrationButton() {
        registrationButton.setText("Sing in");
        registrationButton.setBounds(100, 300, 200, 30);
        registrationButton.setFocusable(false);
        registrationButton.addActionListener(this);
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
        if (source.equals(returnButton)) {
            navigateToAuthenticationPage();
        } else if (source.equals(registrationButton)) {
            authentication.registration(nameField.getText(), passwordField.getText());
            this.dispose();
            if(AuthenticationImpl.getLoggedUser() == null
                    || AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED){
                JOptionPane.showMessageDialog(null, "This account already exist. Please try again.");
            }else if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED) {
                JOptionPane.showMessageDialog(null, "Registration successful");
                this.bookController = new BookControllerImpl();
                navigateToOptionPage();
            }
        }
    }

    private void navigateToAuthenticationPage() {
        this.dispose();
        AuthenticationPage.getInstance().setVisible(true);
    }

    private void navigateToOptionPage(){
        this.dispose();
        new UserOptionPage(bookController).setVisible(true);
    }
}
