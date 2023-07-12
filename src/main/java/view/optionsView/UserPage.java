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
    private final JLabel usernameLabel;
    private final JTextField usernameField;
    private final JButton removeUserButton;
    private final Authentication authentication;
    private int y;
    private final BookController bookController;

    public UserPage(BookController bookController){
        this.returnButton = new JButton();
        this.logoutButton = new JButton();
        this.users = new ArrayList<>();
        this.usernameLabel = new JLabel();
        this.usernameField = new JTextField();
        this.removeUserButton = new JButton();
        this.authentication = AuthenticationImpl.getInstance();
        this.y = 40;
        this.bookController = bookController;
        setupComponents();
    }

    private void setupComponents() {
        setupUsers();
        setupReturnButton();
        setupLogoutButton();
        setupUsernameLabel();
        setupUsernameField();
        setupRemoveUserButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(returnButton);
        this.add(logoutButton);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(removeUserButton);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Users");
        this.setBounds(550, 150, 420, 630);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupRemoveUserButton() {
        removeUserButton.setText("Remove user");
        removeUserButton.setBounds(150, y+60, 130, 20);
        removeUserButton.setFocusable(false);
        removeUserButton.addActionListener(this);
    }

    private void setupUsernameField() {
        usernameField.setBounds(150, y+25, 130, 20);
    }

    private void setupUsernameLabel() {
        usernameLabel.setText("Username: ");
        usernameLabel.setBounds(80, y+15, 80, 40);
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
        List<User> allUsers = authentication.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            this.users.add(new JLabel());
            this.users.get(i).setText(allUsers.get(i).getName());
            this.users.get(i).setBounds(180, y, 190, 60);
            this.add(this.users.get(i));
            y+= 30;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(returnButton)){
            navigateToAdminOptionPage();
        }else if (source.equals(logoutButton)){
            authentication.logout();
            navigateToAuthenticationPage();
        } else if (source.equals(removeUserButton)){
            if (doesUserHaveBooks()){
                JOptionPane.showMessageDialog(null,"This user have booked books. " +
                        "You cannot delete this account before the books are returned.");
            }else {
                authentication.removeUserProfile(usernameField.getText());
            }
            this.dispose();
            new UserPage(bookController);
        }
    }

    private boolean doesUserHaveBooks(){
        boolean doesUserHaveBooks = false;
        for (User user:
             authentication.getAllUsers()) {
            if (user.getName().equals(usernameField.getText())){
                if (!(bookController.getBookedBooksFromUser(user).isEmpty())){
                    doesUserHaveBooks = true;
                }
            }
        }
        return doesUserHaveBooks;
    }

    private void navigateToAuthenticationPage() {
        this.dispose();
        HomePage.getInstance().setVisible(true);
    }

    private void navigateToAdminOptionPage() {
        this.dispose();
        new AdminOptionPage(bookController).setVisible(true);
    }
}
