package view.optionsView;
import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import view.HomePage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserOptionPage extends BaseOptionPage implements ActionListener {
    private final JLabel returnBookLabel;
    private final JLabel newBookLabel;
    private final JButton rentButton;
    private final JButton yourBooksButton;
    private final BookController bookController;
    private final JButton deleteProfileButton;
    private final Authentication authentication;

    public UserOptionPage(BookController bookController){
        super(bookController);
        this.getContentPane().setBackground(Color.ORANGE);
        this.setTitle("Options");
        this.returnBookLabel = new JLabel();
        this.newBookLabel = new JLabel();
        this.rentButton = new JButton();
        this.yourBooksButton = new JButton();
        this.bookController = bookController;
        this.deleteProfileButton = new JButton();
        this.authentication = AuthenticationImpl.getInstance();
        setupComponents();
    }

    public void setupComponents() {
        setupIcon("return book icon.png", 860, 330, 275, 255);
        setupIcon("new book icon.png", 460, 300, 430, 300);
        setupReturnBookLabel();
        setupNewBookLabel();
        setupRentButton();
        setupYourBooksButton();
        setupDeleteProfileButton();
    }

    private void setupReturnBookLabel() {
        returnBookLabel.setText("Return book");
        returnBookLabel.setBounds(950, 600, 100, 20);
        Font biggerFont = new Font(returnBookLabel.getFont().getName(), Font.PLAIN, 16);
        returnBookLabel.setFont(biggerFont);
        this.add(returnBookLabel);
    }

    private void setupNewBookLabel() {
        newBookLabel.setText("New book");
        newBookLabel.setBounds(630, 600, 100, 20);
        Font biggerFont = new Font(newBookLabel.getFont().getName(), Font.PLAIN, 16);
        newBookLabel.setFont(biggerFont);
        this.add(newBookLabel);
    }

    private void setupYourBooksButton() {
        yourBooksButton.setText("Your books");
        yourBooksButton.setBounds(850, 350, 250, 200);
        yourBooksButton.setFocusable(false);
        yourBooksButton.addActionListener(this);
        this.add(yourBooksButton);
    }

    private void setupRentButton() {
        rentButton.setText("Rent");
        rentButton.setBounds(700, 350, 250, 200);
        rentButton.setFocusable(false);
        rentButton.addActionListener(this);
        this.add(rentButton);
    }

    private void setupDeleteProfileButton() {
        deleteProfileButton.setText("Delete profile");
        deleteProfileButton.setBounds(1550, 45, 120, 20);
        deleteProfileButton.setFocusable(false);
        deleteProfileButton.addActionListener(this);
        this.add(deleteProfileButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(rentButton)){
            navigateToPage(this, new RentBookPage(bookController));
        } else if (source.equals(yourBooksButton)) {
            navigateToPage(this, new ReturnBookPage(bookController));
        } else if (source.equals(deleteProfileButton)){
            if (bookController.doesUserHaveBooks(AuthenticationImpl.getLoggedUser().getName())){
                JOptionPane.showMessageDialog(null,"This user have booked books. " +
                        "You cannot delete this account before the books are returned.");
            }else {
                authentication.removeProfile(AuthenticationImpl.getLoggedUser().getName(), "users");
                navigateToPage(this, HomePage.getInstance());
            }
        } else if (source.equals(this.getLogoutButton())){
           authentication.logout();
           navigateToPage(this, HomePage.getInstance());
       } else if(source.equals(this.getReturnButton())){
           navigateToPage(this, HomePage.getInstance());
       }
    }
}
