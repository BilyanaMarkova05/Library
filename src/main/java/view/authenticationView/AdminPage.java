package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookControllerImpl;
import model.userModel.UserStatus;
import view.optionsView.AdminOptionPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame implements ActionListener {
    private final JButton returnButton;
    private final JLabel nameLabel;
    private final JTextField nameField;
    private final JLabel passwordLabel;
    private final JTextField passwordField;
    private final JButton loginButton;
    private final Authentication authentication;

    public AdminPage(){
        this.returnButton = new JButton();
        this.nameLabel = new JLabel();
        this.nameField = new JTextField();
        this.passwordLabel = new JLabel();
        this.passwordField = new JTextField();
        this.loginButton = new JButton();
        this.authentication = AuthenticationImpl.getInstance();
        setupComponents();
    }

    private void setupComponents() {
        setupReturnButton();
        setupNameLabel();
        setupNameField();
        setupPasswordLabel();
        setupPasswordField();
        setupLoginButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(returnButton);
        this.add(nameLabel);
        this.add(nameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);
        this.setTitle("Admin");
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 150, 420, 630);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
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

    private void setupReturnButton() {
        returnButton.setText("return");
        returnButton.setBounds(10, 20, 90, 20);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(returnButton)){
            navigateToAuthenticationPage();
        }else if (source.equals(loginButton)){
            authentication.loginAsAdmin(nameField.getText(), passwordField.getText());
        }
        if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED){
            JOptionPane.showMessageDialog(null, "Login successful");
            navigateToAdminOptionPage();
        }else if (AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED){
            JOptionPane.showMessageDialog(null, "Login failed. Please try again");
        }
    }

    private void navigateToAdminOptionPage() {
        this.dispose();
        new AdminOptionPage(new BookControllerImpl()).setVisible(true);
    }

    private void navigateToAuthenticationPage() {
        this.dispose();
        AuthenticationPage.getInstance().setVisible(true);
    }
}
