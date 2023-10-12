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
        this.setTitle(title);
        this.getContentPane().setBackground(Color.ORANGE);
        setLayout(null);
        this.titleLabel = new JLabel();
        this.nameLabel = new JLabel();
        this.nameField = new JTextField();
        this.passwordLabel = new JLabel();
        this.passwordField = new JPasswordField();
        this.authenticationButton = new JButton();
        this.returnButton = new JButton();
        setupComponents(buttonName);
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

    private void setupComponents(String buttonName) {
        setupIcon();
        setupTitleLabel();
        setupNameLabel();
        setupNameField();
        setupPasswordLabel();
        setupPasswordField();
        setupAuthenticationButton(buttonName);
        setupReturnButton();
    }

    private void setupIcon() {
        ImageIcon icon = new ImageIcon("icon.png");
        Image scaledImage = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(scaledIcon);

        JPanel panel = new JPanel();
        panel.setBounds(500, 150, 300, 205);
        panel.setBackground(Color.ORANGE);
        panel.add(iconLabel);
        this.add(panel);
    }

    private void setupTitleLabel() {
        titleLabel.setText("<html>Library<br>Management<br>System</html>");
        titleLabel.setBounds(800, 130, 405, 250 );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        this.add(titleLabel);
    }

    private void setupReturnButton() {
        returnButton.setText("<");
        returnButton.setBounds(10, 20, 20, 20);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
        this.add(returnButton);
    }

    private void setupAuthenticationButton(String buttonName) {
        authenticationButton.setText(buttonName);
        authenticationButton.setBounds(750, 650, 200, 40);
        authenticationButton.setFocusable(false);
        authenticationButton.addActionListener(this);
        this.add(authenticationButton);
    }

    private void setupPasswordField() {
        passwordField.setBounds(800, 550, 200, 40);
        this.add(passwordField);
    }

    private void setupPasswordLabel() {
        passwordLabel.setText("Password: ");
        passwordLabel.setBounds(700, 550, 90, 40);
        Font biggerFont = new Font(passwordLabel.getFont().getName(), Font.PLAIN, 16);
        passwordLabel.setFont(biggerFont);
        this.add(passwordLabel);
    }

    private void setupNameField() {
        nameField.setBounds(800, 500, 200, 40);
        this.add(nameField);
    }

    private void setupNameLabel() {
        nameLabel.setText("Name: ");
        nameLabel.setBounds(700, 500, 90, 40);
        Font biggerFont = new Font(nameLabel.getFont().getName(), Font.PLAIN, 16);
        nameLabel.setFont(biggerFont);
        this.add(nameLabel);
    }

    public void showMessage(BasePage basePage, String text, Color color, int width) {
        JLabel messageLabel = new JLabel(text);
        messageLabel.setForeground(color);
        messageLabel.setBounds(800, 450, width, 40);
        Font biggerFont = new Font(messageLabel.getFont().getName(), Font.PLAIN, 16);
        messageLabel.setFont(biggerFont);
        basePage.add(messageLabel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
