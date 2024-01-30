package view.optionsView.adminOptionsPages;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import view.HomePage;
import view.authenticationView.RegistrationLibrarianPage;
import view.optionsView.BaseOptionPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminOptionPage extends BaseOptionPage implements ActionListener {
    private final JLabel usersLabel;
    private final JLabel addLibrarianLabel;
    private final JButton usersButton;
    private final JButton addLibrarianButton;
    private final BookController bookController;
    private final Authentication authentication;

    public AdminOptionPage(BookController bookController){
        setLayout(null);
        this.usersLabel = new JLabel();
        this.addLibrarianLabel = new JLabel();
        this.usersButton = new JButton();
        this.addLibrarianButton = new JButton();
        this.bookController = bookController;
        this.authentication = AuthenticationImpl.getInstance();
        setupComponents();
    }

    public void setupComponents() {
        setupUsersLabel();
        setupAddLibrarian();
        setupUsersButton();
        setupRegisterLibrarianButton();
        setupFrame();
    }

    private void setupAddLibrarian() {
        addLibrarianLabel.setText("add librarian");
        addLibrarianLabel.setBounds(972, 400, 300, 300);
        addLibrarianLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(addLibrarianLabel);
    }

    private void setupUsersLabel() {
        usersLabel.setText("users");
        usersLabel.setBounds(688, 400, 300, 300);
        usersLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(usersLabel);
    }

    private void setupRegisterLibrarianButton() {
        addLibrarianButton.setText("add librarian");
        addLibrarianButton.setBounds(930, 340, 200, 200);
        addLibrarianButton.setFocusable(false);
        addLibrarianButton.addActionListener(this);
    }

    private void setupFrame() {
        setupIcon("icon.png", 590,140, 100, 60 );
        setupTitleLabel("Library Management System", 30, 650, 150, 500, 50);
        setupIcon("users icon.png", 510, 240, 400, 400);
        setupIcon("add librarian icon.png", 930, 340, 200, 200);
        this.add(usersButton);
        this.add(addLibrarianButton);
        this.getContentPane().setBackground(Color.ORANGE);
        this.setTitle("Options");
    }

    private void setupUsersButton() {
        usersButton.setText("Users");
        usersButton.setBounds(610, 340, 200, 200);
        usersButton.setFocusable(false);
        usersButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(this.getReturnButton())){
            navigateToPage(this, HomePage.getInstance());
        } else if (source.equals(this.getLogoutButton())){
            authentication.logout();
            navigateToPage(this, HomePage.getInstance());
        } else if (source.equals(usersButton)){
            navigateToPage(this, new UserPage(bookController));
        } else if (source.equals(addLibrarianButton)){
            navigateToPage(this, new RegistrationLibrarianPage("Registration", "Sign in"));
        }
    }
}
