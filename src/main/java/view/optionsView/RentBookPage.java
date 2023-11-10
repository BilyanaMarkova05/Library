package view.optionsView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import view.HomePage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RentBookPage extends BaseOptionPage implements ActionListener {
    private final Authentication authentication;
    private final JButton deleteProfileButton;
    private final BookController bookController;

    private final List<JLabel> allBooks;
    private final List<JButton> buttons;

    public RentBookPage(BookController bookController) {
        this.authentication = AuthenticationImpl.getInstance();
        this.bookController = bookController;
        this.deleteProfileButton = new JButton();
        this.allBooks = new ArrayList<>();
        this.buttons = new ArrayList<>();
        setupComponents();
    }

    private void setupComponents() {
        setupIcon("icon.png", 590,80, 100, 60 );
        setupTitleLabel("Library Management System", 30, 650, 90, 500, 50);
        setupDeleteProfileButton();
        setupButtonArray();
        setupAllBooksList(bookController.getAllBooks(), buttons, allBooks);
    }

    private void setupButtonArray(){
        for (int i = 0; i < bookController.getAllBooks().size(); i++) {
            buttons.add(new JButton());
            setupButton(i);
        }
    }

    private void setupButton(int i) {
        buttons.get(i).setText("Rent");
        buttons.get(i).setSize(40, 20);
        buttons.get(i).setFocusable(false);
        buttons.get(i).addActionListener(this);
    }

    private void setupDeleteProfileButton() {
        deleteProfileButton.setText("Delete profile");
        deleteProfileButton.setBounds(1550, 45, 120, 20);
        deleteProfileButton.setFocusable(false);
        deleteProfileButton.addActionListener(this);
        this.add(deleteProfileButton);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < buttons.size(); i++) {
            if (source.equals(buttons.get(i))){
             bookController.rentBook(bookController.getAllBooks().get(i).getName());
            }
        }
        if (source.equals(deleteProfileButton)) {
            if (bookController.doesUserHaveBooks(AuthenticationImpl.getLoggedUser().getName())) {
                JOptionPane.showMessageDialog(null, "This user have booked books. " +
                        "You cannot delete this account before the books are returned.");
            } else {
                authentication.removeProfile(AuthenticationImpl.getLoggedUser().getName(), "users");
                navigateToPage(this, HomePage.getInstance());
            }
        } else if (source.equals(this.getLogoutButton())) {
            authentication.logout();
            navigateToPage(this, HomePage.getInstance());
        } else if(source.equals(getReturnButton())){
            navigateToPage(this, new UserOptionPage(bookController));
        }
    }
}
