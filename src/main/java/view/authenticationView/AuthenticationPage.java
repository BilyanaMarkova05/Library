package view.authenticationView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationPage extends JFrame implements ActionListener {

    private final ImageIcon icon;
    private final JLabel titleLabel;
    private final JLabel nameLabel;
    private final JTextField nameField;
    private final JLabel passwordLabel;
    private final JPasswordField passwordField;
    private final JButton authenticationButton;
    private final JButton returnButton;


    public AuthenticationPage(String title, String buttonName) {
        this.icon = new ImageIcon("icon.jpeg");
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

    public void navigateToPage(JFrame jFrame){
        this.dispose();
        jFrame.setVisible(true);
    }

    private void setupComponents(String title, String buttonName) {
        setupIcon();
        setupTitleLabel();
        setupNameLabel();
        setupNameField();
        setupPasswordLabel();
        setupPasswordField();
        setupAuthenticationButton(buttonName);
        setupReturnButton();
        setupFrame(title);
    }

    private void setupIcon() {
        JLabel iconLabel = new JLabel(this.icon);
        iconLabel.setBounds(0, 90, 200, 200);
        this.add(iconLabel);
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.ORANGE);
        this.setTitle(title);
        this.setBounds(550, 150, 420, 630);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupReturnButton() {
        returnButton.setText("<");
        returnButton.setBounds(10, 20, 50, 20);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
        returnButton.setBackground(Color.RED);
    }

    private void setupAuthenticationButton(String buttonName) {
        authenticationButton.setText(buttonName);
        authenticationButton.setBounds(100, 400, 200, 30);
        authenticationButton.setBackground(Color.ORANGE);
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
