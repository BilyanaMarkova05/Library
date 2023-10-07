package view.authenticationView;

import view.BasePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends BasePage implements ActionListener {
    private static HomePage instance;
    private final JLabel titleLabel;
    private final JButton registrationButton;
    private final JButton loginButton;
    private final JButton librarianButton;
    private final JButton adminButton;

    public static HomePage getInstance() {
        if (instance == null) instance = new HomePage();
        return instance;
    }

    public HomePage(){
        this.registrationButton = new JButton();
        this.titleLabel = new JLabel();
        this.loginButton = new JButton();
        this.adminButton = new JButton();
        this.librarianButton = new JButton();
        setupComponents();
    }

    private void setupComponents() {
        setupFrame();
        setupTitleLabel();
        setupRegistrationButton();
        setupLoginButton();
        setupLibrarianButton();
        setupAdminButton();
    }

    private void setupFrame() {
        this.setTitle("Home");
        this.add(titleLabel);
        this.add(registrationButton);
        this.add(loginButton);
        this.add(adminButton);
        this.add(librarianButton);

    }

    private void setupTitleLabel() {
        titleLabel.setText("<html>Library Management<br><center>System</html>");
        titleLabel.setBounds(600, -10, 500, 500 );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setVisible(true);
    }

    private void setupRegistrationButton() {
        registrationButton.setText("Sing in");
        registrationButton.setBounds(750, 370, 200, 40);
        registrationButton.setFocusable(false);
        registrationButton.addActionListener(this);
    }

    private void setupLoginButton() {
        loginButton.setText("Login");
        loginButton.setBounds(750, 420, 200, 40);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
    }

    private void setupLibrarianButton() {
        librarianButton.setText("Librarian");
        librarianButton.setBounds(750, 520, 200, 40);
        librarianButton.setFocusable(false);
        librarianButton.addActionListener(this);
    }
    private void setupAdminButton() {
        adminButton.setText("Admin");
        adminButton.setBounds(750, 620, 200, 40);
        adminButton.setFocusable(false);
        adminButton.addActionListener(this);
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
        new RegistrationUserPage().setVisible(true);
    }

    private void navigateToLoginLibrarianPage() {
        this.dispose();
        new LoginLibrarianPage();
    }
}
