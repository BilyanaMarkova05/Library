package view.optionsView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import model.userModel.User;
import view.authenticationView.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserPage extends JFrame implements ActionListener {
    private final JButton returnButton;
    private final JButton logoutButton;
    private final List<JLabel> users;
    private final List<JLabel> librarians;
    private final JLabel usernameLabel;
    private final JTextField usernameField;
    private final JButton removeUserButton;
    private final JLabel librarianNameLabel;
    private final JTextField librarianNameField;
    private final JButton removeLibrarianButton;
    private final Authentication authentication;
    private int yUsers;
    private int yLibrarians;
    private final BookController bookController;

    public UserPage(BookController bookController){
        this.returnButton = new JButton();
        this.logoutButton = new JButton();
        this.users = new ArrayList<>();
        this.librarians = new ArrayList<>();
        this.usernameLabel = new JLabel();
        this.usernameField = new JTextField();
        this.removeUserButton = new JButton();
        this.librarianNameLabel = new JLabel();
        this.librarianNameField = new JTextField();
        this.removeLibrarianButton = new JButton();
        this.authentication = AuthenticationImpl.getInstance();
        this.yUsers = 60;
        this.yLibrarians = 60;
        this.bookController = bookController;
        setupComponents();
    }

    private void setupComponents() {
        setupUsers();
        setupLibrarians();
        setupReturnButton();
        setupLogoutButton();
        setupUsernameLabel();
        setupUsernameField();
        setupRemoveUserButton();
        setupLibrarianNameLabel();
        setupLibrarianNameField();
        setupRemoveLibrarianButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(returnButton);
        this.add(logoutButton);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(removeUserButton);
        this.add(librarianNameLabel);
        this.add(librarianNameField);
        this.add(removeLibrarianButton);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Users");
        this.setBounds(550, 150, 420, 630);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupRemoveLibrarianButton() {
        removeLibrarianButton.setText("Remove librarian");
        removeLibrarianButton.setBounds(260, yLibrarians +60, 130, 20);
        removeLibrarianButton.setFocusable(false);
        removeLibrarianButton.addActionListener(this);
    }

    private void setupLibrarianNameField() {
        librarianNameField.setBounds(290, yLibrarians +25, 90, 20);
    }

    private void setupLibrarianNameLabel() {
        librarianNameLabel.setText("<html>Librarian<br>name: </html>");
        librarianNameLabel.setBounds(220, yLibrarians +15, 80, 40);
    }

    private void setupRemoveUserButton() {
        removeUserButton.setText("Remove user");
        removeUserButton.setBounds(60, yUsers +60, 130, 20);
        removeUserButton.setFocusable(false);
        removeUserButton.addActionListener(this);
    }

    private void setupUsernameField() {
        usernameField.setBounds(90, yUsers +25, 90, 20);
    }

    private void setupUsernameLabel() {
        usernameLabel.setText("Username: ");
        usernameLabel.setBounds(20, yUsers +15, 80, 40);
    }

    private void setupLogoutButton() {
        logoutButton.setText("Logout");
        logoutButton.setBounds(300, 20, 90, 20);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
    }

    private void setupReturnButton() {
        returnButton.setText("return");
        returnButton.setBounds(10, 20, 90, 20);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupUsers() {
        List<User> allUsers = authentication.getAllUsers("users");
        for (int i = 0; i < allUsers.size(); i++) {
            this.users.add(new JLabel());
            this.users.get(i).setText(allUsers.get(i).getName());
            this.users.get(i).setBounds(120, yUsers, 190, 60);
            this.add(this.users.get(i));
            yUsers += 30;
        }
    }

    private void setupLibrarians() {
        List<User> allLibrarians = authentication.getAllUsers("librarians");
        for (int i = 0; i < allLibrarians.size(); i++) {
            this.librarians.add(new JLabel());
            this.librarians.get(i).setText(allLibrarians.get(i).getName());
            this.librarians.get(i).setBounds(280, yLibrarians, 190, 60);
            this.add(this.librarians.get(i));
            yLibrarians += 30;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(returnButton)){
            navigateToAdminOptionPage();
        }else if (source.equals(logoutButton)){
            authentication.logout();
            navigateToHomePage();
        } else if (source.equals(removeUserButton)){
            if (bookController.doesUserHaveBooks(usernameField.getText())){
                JOptionPane.showMessageDialog(null,"This user have booked books. " +
                        "You cannot delete this account before the books are returned.");
            }else {
                authentication.removeProfile(usernameField.getText(), "users");
            }
            this.dispose();
            new UserPage(bookController);
        } else if (source.equals(removeLibrarianButton)) {
            authentication.removeProfile(librarianNameField.getText(), "librarians");
            this.dispose();
            new UserPage(bookController);
        }
    }

    private void navigateToHomePage() {
        this.dispose();
        HomePage.getInstance().setVisible(true);
    }

    private void navigateToAdminOptionPage() {
        this.dispose();
        new AdminOptionPage(bookController).setVisible(true);
    }
}
