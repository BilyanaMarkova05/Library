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

public class LibOptionPage extends BaseOptionPage implements ActionListener {
    private static LibOptionPage instance;
    private final Authentication authentication;
    private final List<JButton> buttons;
    private final List<JLabel> allBooks;
    private final JButton deleteProfileButton;
    private final JButton addBookButton;
    private final BookController bookController;
    public LibOptionPage(BookController bookController) {
        super(bookController);
        this.setTitle("Options");
        this.getContentPane().setBackground(Color.ORANGE);
        this.authentication = AuthenticationImpl.getInstance();
        this.buttons = new ArrayList<>();
        this.bookController = bookController;
        this.deleteProfileButton = new JButton();
        this.allBooks = new ArrayList<>();
        this.addBookButton = new JButton();
        setupComponents();
    }

    public static LibOptionPage getInstance(BookController bookController) {
        if (instance == null) {
            instance = new LibOptionPage(bookController);
        }
        return instance;
    }

    private void setupComponents() {
        setupIcon("icon.png", 590,80, 100, 60 );
        setupTitleLabel("Library Management System", 30, 650, 90, 500, 50);
        setupDeleteProfileButton(deleteProfileButton);
        setupButtonArray();
        setupAddBookButton();
        setupAllBooksList(bookController.getAllBooks(), buttons, allBooks);
    }

    private void setupAddBookButton() {
        addBookButton.setText("Add book");
        addBookButton.setBounds(750, 800, 200, 50);
        addBookButton.setFocusable(false);
        addBookButton.addActionListener(this);
        this.add(addBookButton);
    }

    private void setupButtonArray(){
        for (int i = 0; i < bookController.getAllBooks().size(); i++) {
            buttons.add(new JButton());
            setupButton(i);
        }
    }

    private void setupButton(int i) {
        buttons.get(i).setText("Remove");
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
        }
        for (int i = 0; i < buttons.size(); i++) {
            if (source.equals(buttons.get(i))) {
                bookController.removeBook(bookController.getAllBooks().get(i).getName());
                navigateToPage(this, new LibOptionPage(bookController));
            }
        }
    }
}
