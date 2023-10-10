package view.authenticationView;

import view.BasePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationPage extends BasePage implements ActionListener {

    private final JLabel titleLabel;
    private final JLabel nameLabel;
    private final JTextField nameField;
    private final JLabel passwordLabel;
    private final JPasswordField passwordField;
    private final JButton authenticationButton;
    private final JButton returnButton;


    public AuthenticationPage(String title, String buttonName) {
        this.titleLabel = new JLabel();
        this.nameLabel = new JLabel();
        this.nameField = new JTextField();
        this.passwordLabel = new JLabel();
        this.passwordField = new JPasswordField();
        this.authenticationButton = new JButton();
        this.returnButton = new JButton();
        setupComponents(title, buttonName);
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getAuthenticationButton() {
        return authenticationButton;
    }

    private void setupComponents(String title, String buttonName) {
        setupTitleLabel();
        setupNameLabel();
        setupNameField();
        setupPasswordLabel();
        setupPasswordField();
        setupAuthenticationButton(buttonName);
        setupReturnButton();
        setupFrame(title);
    }

    private void setupTitleLabel() {
        titleLabel.setText("<html>Library<br>Management System</html>");
        titleLabel.setBounds(200, 100, 200, 200 );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
    }

    private void setupFrame(String title) {
        this.add(titleLabel);
        this.add(nameLabel);
        this.add(nameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(authenticationButton);
        this.add(returnButton);
        this.setTitle(title);
    }

    private void setupReturnButton() {
        returnButton.setText("<");
        returnButton.setBounds(10, 20, 20, 20);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupAuthenticationButton(String buttonName) {
        authenticationButton.setText(buttonName);
        authenticationButton.setBounds(100, 400, 200, 30);
        authenticationButton.setFocusable(false);
        authenticationButton.addActionListener(this);
    }

    private void setupPasswordField() {
        passwordField.setBounds(160, 340, 150, 30);
    }

    private void setupPasswordLabel() {
        passwordLabel.setText("Password: ");
        passwordLabel.setBounds(90, 340, 80, 40);
    }

    private void setupNameField() {
        nameField.setBounds(160, 290, 150, 30);
    }

    private void setupNameLabel() {
        nameLabel.setText("Name: ");
        nameLabel.setBounds(90, 290, 80, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
