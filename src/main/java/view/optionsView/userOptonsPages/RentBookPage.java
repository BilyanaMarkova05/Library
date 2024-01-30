package view.optionsView.userOptonsPages;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import view.HomePage;
import view.optionsView.BaseOptionPage;

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
    private final JButton searchByAuthorButton;
    private final JButton searchByGenreButton;
    private final JButton searchByTitle;

    public RentBookPage(BookController bookController) {
        this.authentication = AuthenticationImpl.getInstance();
        this.bookController = bookController;
        this.deleteProfileButton = new JButton();
        this.allBooks = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.searchByAuthorButton = new JButton();
        this.searchByGenreButton = new JButton();
        this.searchByTitle = new JButton();
        setupComponents();
    }

    private void setupComponents() {
        setupIcon("icon.png", 590,80, 100, 60 );
        setupTitleLabel("Library Management System", 30, 650, 90, 500, 50);
        setupDeleteProfileButton(deleteProfileButton);
        setupSearchByAuthorButton();
        setupSearchByGenreButton();
        setupSearchByTitleButton();
        setupBooksList(bookController.getAllBooks(), setupButtonArray(bookController.getAllBooks().size()), allBooks, null, 300, 500, 100, 500);
    }

    private void setupSearchByTitleButton() {
        searchByTitle.setText("search by title");
        searchByTitle.addActionListener(this);
        searchByTitle.setBounds(550, 210, 150,45);
        this.add(searchByTitle);
    }

    private void setupSearchByGenreButton() {
        searchByGenreButton.setText("search by genre");
        searchByGenreButton.addActionListener(this);
        searchByGenreButton.setBounds(1000, 210, 150, 45);
        this.add(searchByGenreButton);
    }

    private void setupSearchByAuthorButton() {
        searchByAuthorButton.setText("search by author");
        searchByAuthorButton.addActionListener(this);
        searchByAuthorButton.setBounds(780, 210, 150, 45);
        this.add(searchByAuthorButton);
    }

    private List<JButton> setupButtonArray(int length){
        for (int i = 0; i < length; i++) {
            buttons.add(new JButton());
            setupButton(i);
        }
        return buttons;
    }

    private void setupButton(int i) {
        buttons.get(i).setText("Rent");
        buttons.get(i).setSize(40, 20);
        buttons.get(i).setFocusable(false);
        buttons.get(i).addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < buttons.size(); i++) {
            if (source.equals(buttons.get(i))) {
                bookController.rentBook(bookController.getAllBooks().get(i).getName());
                navigateToPage(this, new RentBookPage(bookController));
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
        bookController.truncateSearchedBooks();
        if (source.equals(searchByTitle)) {
            new SearchPage(bookController,  "title: ");
        } else if (source.equals(searchByAuthorButton)){
            new SearchPage(bookController,  "author: ");
        } else if (source.equals(searchByGenreButton)){
            new SearchPage(bookController, "genre: ");
        }
    }
}
