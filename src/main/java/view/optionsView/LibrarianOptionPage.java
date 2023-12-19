package view.optionsView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import view.HomePage;
import view.authenticationView.LoginLibrarianPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LibrarianOptionPage extends BaseOptionPage implements ActionListener {
    private static LibrarianOptionPage instance;
    private final Authentication authentication;
    private final List<JButton> buttons;
    private final List<JLabel> allBooks;
    private final List<JButton> editButtons;
    private final JButton addBookButton;
    private final JButton bookedBooksButton;
    private final BookController bookController;
    public LibrarianOptionPage(BookController bookController) {
        this.setTitle("Options");
        this.getContentPane().setBackground(Color.ORANGE);
        this.authentication = AuthenticationImpl.getInstance();
        this.buttons = new ArrayList<>();
        this.bookController = bookController;
        this.allBooks = new ArrayList<>();
        this.editButtons = new ArrayList<>();
        this.addBookButton = new JButton();
        this.bookedBooksButton = new JButton();
        setupComponents();
    }

    public static LibrarianOptionPage getInstance(BookController bookController) {
        if (instance == null) {
            instance = new LibrarianOptionPage(bookController);
        }
        return instance;
    }

    public List<JButton> getEditButtons() {
        return editButtons;
    }

    private void setupComponents() {
        setupIcon("icon.png", 590,80, 100, 60 );
        setupTitleLabel("Library Management System", 30, 650, 90, 500, 50);
        setupIcon("booked books icon.png",1550, 25, 100, 90 );
        setupTitleLabel("Booked books", 13, 1550, 110, 90, 20);
        setupButtonArray(buttons, "Remove");
        setupButtonArray(editButtons, "Edit");
        setupAddBookButton();
        setupBookedBookButton();
        setupBooksList(bookController.getAllBooks(), buttons, allBooks,editButtons);
    }

    private void setupBookedBookButton() {
        bookedBooksButton.setText("Booked books");
        bookedBooksButton.setBounds(1570, 40, 50, 50);
        bookedBooksButton.setFocusable(false);
        bookedBooksButton.addActionListener(this);
        this.add(bookedBooksButton);
    }

    private void setupAddBookButton() {
        addBookButton.setText("Add book");
        addBookButton.setBounds(750, 800, 200, 50);
        addBookButton.setFocusable(false);
        addBookButton.addActionListener(this);
        this.add(addBookButton);
    }

    private void setupButtonArray(List<JButton> buttons, String text){
        for (int i = 0; i < bookController.getAllBooks().size(); i++) {
            buttons.add(new JButton());
            setupButton(buttons, i, text);
        }
    }

    private void setupButton(List<JButton> buttons, int i, String text) {
        buttons.get(i).setText(text);
        buttons.get(i).setSize(40, 20);
        buttons.get(i).setFocusable(false);
        buttons.get(i).addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(getReturnButton())){
            navigateToPage(this, new LoginLibrarianPage());
        } else if (source.equals(getLogoutButton())) {
            authentication.logout();
            navigateToPage(this, HomePage.getInstance());
        } else if (source.equals(addBookButton)){
            new AddBookPage(bookController);
        } else if (source.equals(bookedBooksButton)) {
            new BookedBooksPage(bookController);
        }
        for (int i = 0; i < buttons.size(); i++) {
            if (source.equals(buttons.get(i))) {
                bookController.removeBook(bookController.getAllBooks().get(i).getName());
                navigateToPage(this, new LibrarianOptionPage(bookController));
            }
        }
        for (int i = 0; i < editButtons.size(); i++) {
            if (source.equals(editButtons.get(i))){
                new EditBookPage(bookController);
            }
        }
    }
}
