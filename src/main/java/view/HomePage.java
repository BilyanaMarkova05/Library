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
    private int xButtons;
    private int yButtons;
    private int widthButtons;
    private int heightButtons;

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
        this.xButtons = 700;
        this.yButtons = 480;
        this.widthButtons = 300;
        this.heightButtons = 50;
        setupComponents();
    }

    private void setupComponents() {
        setupIcon("icon.png",700, 30, 300, 200);
        setupRegistrationButton();
        setupLoginButton();
        setupLibrarianButton();
        setupAdminButton();
        setupTitleLabel();
    }

    private void setupTitleLabel() {
        JLabel titleLabel = new JLabel("<html>Library Management<br><center> System</html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));

        JPanel panel = new JPanel();
        panel.setBounds(400, 250, 900, 215);
        panel.add(titleLabel);
        panel.setBackground(Color.ORANGE);
        this.add(panel);
    }

    private void setupRegistrationButton() {
        registrationButton.setText("Sign in");
        registrationButton.setBounds(xButtons, yButtons, widthButtons, heightButtons);
        this.add(registrationButton);
        registrationButton.addActionListener(this);
    }

    private void setupLoginButton() {
        loginButton.setText("Login");
        loginButton.setBounds(xButtons, yButtons+60, widthButtons, heightButtons);
        add(loginButton);
        loginButton.addActionListener(this);
    }


    private void setupLibrarianButton() {
        librarianButton.setText("Librarian");
        librarianButton.setBounds(xButtons, yButtons+120, widthButtons, heightButtons);
        add(librarianButton);
        librarianButton.addActionListener(this);
    }
    private void setupAdminButton() {
        adminButton.setText("Admin");
        adminButton.setBounds(xButtons, yButtons + 180, widthButtons, heightButtons);
        add(adminButton);
        adminButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(registrationButton)) {
            navigateToPage(this, new RegistrationUserPage());
        }else if (source.equals(loginButton)){
            navigateToPage(this, new LoginPage());
        }else if(source.equals(librarianButton)){
            navigateToPage(this, new LoginLibrarianPage());
        }else if (source.equals(adminButton)){
            navigateToPage(this, new AdminPage());
        }
    }
}
