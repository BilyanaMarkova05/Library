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


public class UserOptionPage extends BaseOptionPage implements ActionListener {
    private final JButton rentButton;
    private final JButton yourBooksButton;
    private final JLabel bookNameLabel;
    private final JTextField bookNameField;
    private final BookController bookController;
    private final JButton deleteProfileButton;
    private final Authentication authentication;

    public UserOptionPage(BookController bookController){
        super(bookController);
        this.rentButton = new JButton();
        this.yourBooksButton = new JButton();
        this.bookNameLabel = new JLabel();
        this.bookNameField = new JTextField();
        this.bookController = bookController;
        this.deleteProfileButton = new JButton();
        this.authentication = AuthenticationImpl.getInstance();
        setupComponents();

    }

    private void setupComponents() {
        setupRentButton();
        setupYourBooksButton();
        setupBookNameLabel();
        setupBookNameField();
        setupDeleteProfileButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(rentButton);
        this.add(yourBooksButton);
        this.add(bookNameLabel);
        this.add(bookNameField);
        this.add(deleteProfileButton);
        this.getContentPane().setBackground(Color.ORANGE);
        this.setTitle("Options");
    }

    private void setupBookNameField() {
        bookNameField.setBounds(150, this.getY()+25, 130, 20);
    }

    private void setupBookNameLabel() {
        bookNameLabel.setText("Book name: ");
        bookNameLabel.setBounds(80, this.getY()+15, 80, 40);
    }

    private void setupYourBooksButton() {
        yourBooksButton.setText("Your books");
        yourBooksButton.setBounds(160, this.getY()+90, 100, 20);
        yourBooksButton.setFocusable(false);
        yourBooksButton.addActionListener(this);
    }

    private void setupRentButton() {
        rentButton.setText("Rent");
        rentButton.setBounds(160, this.getY()+60, 100, 20);
        rentButton.setFocusable(false);
        rentButton.addActionListener(this);
    }

    private void setupDeleteProfileButton() {
        deleteProfileButton.setText("Delete profile");
        deleteProfileButton.setBounds(270, 45, 120, 20);
        deleteProfileButton.setFocusable(false);
        deleteProfileButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
       if (source.equals(rentButton)){
            bookController.rentBook(bookNameField.getText());
            navigateToUserOptionPage();
        } else if (source.equals(yourBooksButton)) {
            navigateToReturnPage();
        } else if (source.equals(deleteProfileButton)){
            if (doesUserHaveBooks()){
                JOptionPane.showMessageDialog(null,"This user have booked books. " +
                        "You cannot delete this account before the books are returned.");
            }else {
                authentication.removeProfile(AuthenticationImpl.getLoggedUser().getName(), "users");
                navigateToAuthenticationPage();
            }
        } else if (source.equals(this.getLogoutButton())){
           authentication.logout();
           navigateToHomePage();
       } else if(source.equals(this.getReturnButton())){
           navigateToHomePage();
       }
    }

    private boolean doesUserHaveBooks(){
        boolean doesUserHaveBooks = false;
        for (User user:
                authentication.getAllUsers("users")) {
            if (user.getName().equals(AuthenticationImpl.getLoggedUser().getName())){
                if (!(bookController.getBookedBooksFromUser(user).isEmpty())){
                    doesUserHaveBooks = true;
                }
            }
        }
        return doesUserHaveBooks;
    }

    private void navigateToUserOptionPage() {
        this.dispose();
        new UserOptionPage(bookController);
    }

    private void navigateToAuthenticationPage() {
        this.dispose();
        HomePage.getInstance().setVisible(true);
    }

    private void navigateToReturnPage() {
        this.dispose();
        new ReturnBookPage(bookController).setVisible(true);
    }
    private void navigateToHomePage() {
        this.dispose();
        HomePage.getInstance().setVisible(true);
    }
}
