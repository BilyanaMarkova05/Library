package view;

import view.authenticationView.AdminPage;
import view.authenticationView.LoginLibrarianPage;
import view.authenticationView.LoginPage;
import view.authenticationView.RegistrationUserPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends BasePage implements ActionListener {
    private static HomePage instance;
    private final JButton registrationButton;
    private final JButton loginButton;
    private final JButton librarianButton;
    private final JButton adminButton;

    public static HomePage getInstance() {
        if (instance == null) instance = new HomePage();
        return instance;
    }

    public HomePage(){
        setLayout(null);
        setTitle("Home");
        this.getContentPane().setBackground(Color.ORANGE);
        this.registrationButton = new JButton();
        this.loginButton = new JButton();
        this.adminButton = new JButton();
        this.librarianButton = new JButton();
        setupComponents();
    }

    private void setupComponents() {
        setupIcon();
        setupRegistrationButton();
        setupLoginButton();
        setupLibrarianButton();
        setupAdminButton();
        setupTitleLabel();
    }

    private void setupIcon() {
        ImageIcon icon = new ImageIcon("icon.jpeg");
        Image scaledImage = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(scaledIcon);

        JPanel panel = new JPanel();
        panel.setBounds(600, 30, 500, 205);
        panel.setBackground(Color.ORANGE);
        panel.add(iconLabel);
        this.add(panel);
    }

    private void setupTitleLabel() {
        JLabel titleLabel = new JLabel("<html>Library Management<br><center> System</html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 90));

        JPanel panel = new JPanel();
        panel.setBounds(400, 250, 900, 215);
        panel.add(titleLabel);
        panel.setBackground(Color.ORANGE);
        this.add(panel);
    }

    private void setupRegistrationButton() {
        registrationButton.setText("Sign in");
        registrationButton.setBounds(750, 520, 200, 40);
        this.add(registrationButton);
        registrationButton.addActionListener(this);
    }

    private void setupLoginButton() {
        loginButton.setText("Login");
        loginButton.setBounds(750, 570, 200, 40);
        add(loginButton);
        loginButton.addActionListener(this);
    }


    private void setupLibrarianButton() {
        librarianButton.setText("Librarian");
        librarianButton.setBounds(750, 620, 200, 40);
        add(librarianButton);
        librarianButton.addActionListener(this);
    }
    private void setupAdminButton() {
        adminButton.setText("Admin");
        adminButton.setBounds(750, 670, 200, 40);
        add(adminButton);
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
