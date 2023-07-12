package view.authenticationView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationPage extends JFrame implements ActionListener {

    private final JLabel nameLabel;
    private final JTextField nameField;
    private final JLabel passwordLabel;
    private final JPasswordField passwordField;
    private final JButton authenticationButton;
    private final JButton returnButton;


    public AuthenticationPage(String title, String buttonName) {
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

    public void navigateToPage(JFrame jFrame){
        this.dispose();
        jFrame.setVisible(true);
    }

    private void setupComponents(String title, String buttonName) {
        setupNameLabel();
        setupNameField();
        setupPasswordLabel();
        setupPasswordField();
        setupAuthenticationButton(buttonName);
        setupReturnButton();
        setupFrame(title);
    }

    private void setupFrame(String title) {
        this.add(nameLabel);
        this.add(nameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(authenticationButton);
        this.add(returnButton);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle(title);
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

    private void setupAuthenticationButton(String buttonName) {
        authenticationButton.setText(buttonName);
        authenticationButton.setBounds(100, 300, 200, 30);
        authenticationButton.setFocusable(false);
        authenticationButton.addActionListener(this);
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

    }
}
