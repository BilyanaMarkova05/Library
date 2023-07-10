package view.authenticationView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationPage extends JFrame implements ActionListener {
    private static AuthenticationPage instance;
    private final JButton registrationButton;
    private final JButton loginButton;
    private final JButton librarianButton;
    private final JButton adminButton;

    public static AuthenticationPage getInstance() {
        if (instance == null) instance = new AuthenticationPage();
        return instance;
    }

    public AuthenticationPage(){
        this.registrationButton = new JButton();
        this.loginButton = new JButton();
        this.adminButton = new JButton();
        this.librarianButton = new JButton();
        setupComponents();
    }

    private void setupComponents() {
        setupAdminButton();
        setupRegistrationButton();
        setupLoginButton();
        setupLibrarianButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(registrationButton);
        this.add(loginButton);
        this.add(adminButton);
        this.add(librarianButton);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Authentication");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 150, 420, 630);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupAdminButton() {
        adminButton.setText("Admin");
        adminButton.setBounds(100, 320, 200, 40);
        adminButton.setFocusable(false);
        adminButton.addActionListener(this);
    }

    private void setupLibrarianButton() {
        librarianButton.setText("Librarian");
        librarianButton.setBounds(100, 270, 200, 40);
        librarianButton.setFocusable(false);
        librarianButton.addActionListener(this);
    }

    private void setupLoginButton() {
        loginButton.setText("Login");
        loginButton.setBounds(100, 220, 200, 40);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
    }

    private void setupRegistrationButton() {
        registrationButton.setText("Sing in");
        registrationButton.setBounds(100, 170, 200, 40);
        registrationButton.setFocusable(false);
        registrationButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(registrationButton)) {
            navigateToRegistrationPage();
        }else if (source.equals(loginButton)){
            navigateToLoginPage();
        }else if(source.equals(librarianButton)){
            navigateToLoginLibrarianPage();
        }else if (source.equals(adminButton)){
            navigateToAdminPage();
        }
    }

    private void navigateToAdminPage() {
        this.dispose();
        new AdminPage();
    }

    private void navigateToLoginPage() {
        this.dispose();
        new LoginPage();
    }

    private void navigateToRegistrationPage() {
        this.dispose();
        new RegistrationPage().setVisible(true);
    }

    private void navigateToLoginLibrarianPage() {
        this.dispose();
        new LoginLibrarianPage();
    }
}
