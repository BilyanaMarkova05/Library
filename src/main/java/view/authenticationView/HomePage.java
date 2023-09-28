package view.authenticationView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {
    private final ImageIcon icon;
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
        this.icon = new ImageIcon("icon.jpeg");
        this.registrationButton = new JButton();
        this.titleLabel = new JLabel();
        this.loginButton = new JButton();
        this.adminButton = new JButton();
        this.librarianButton = new JButton();
        setupComponents();
    }

    private void setupComponents() {
        setupIcon();
        setupTitleLabel();
        setupAdminButton();
        setupRegistrationButton();
        setupLoginButton();
        setupLibrarianButton();
        setupFrame();
    }

    private void setupIcon() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        JLabel iconLabel = new JLabel(this.icon);
        iconLabel.setBounds(105, -10, 200, 200);
        this.add(iconLabel);
        this.setVisible(true);
    }

    private void setupTitleLabel() {
        titleLabel.setText("<html>Library Management<br><center>System</html>");
        titleLabel.setBounds(60, -30, 400, 400 );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
    }

    private void setupFrame() {
        this.add(registrationButton);
        this.add(titleLabel);
        this.add(loginButton);
        this.add(adminButton);
        this.add(librarianButton);
        this.getContentPane().setBackground(Color.ORANGE);
        this.setTitle("Authentication");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 150, 420, 630);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupAdminButton() {
        adminButton.setText("Admin");
        adminButton.setBounds(100, 420, 200, 40);
        adminButton.setFocusable(false);
        adminButton.addActionListener(this);
    }

    private void setupLibrarianButton() {
        librarianButton.setText("Librarian");
        librarianButton.setBounds(100, 370, 200, 40);
        librarianButton.setFocusable(false);
        librarianButton.addActionListener(this);
    }

    private void setupLoginButton() {
        loginButton.setText("Login");
        loginButton.setBounds(100, 320, 200, 40);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
    }

    private void setupRegistrationButton() {
        registrationButton.setText("Sing in");
        registrationButton.setBounds(100, 270, 200, 40);
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
        new RegistrationUserPage().setVisible(true);
    }

    private void navigateToLoginLibrarianPage() {
        this.dispose();
        new LoginLibrarianPage();
    }
}