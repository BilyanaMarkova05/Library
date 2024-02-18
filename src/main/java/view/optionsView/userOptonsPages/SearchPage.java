package view.optionsView.userOptonsPages;

import controller.BookController;
import model.bookModel.Book;
import view.optionsView.BaseOptionPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class SearchPage extends BaseOptionPage implements ActionListener {
    private final JButton closeButton;
    private final List<Book> allBooks;
    private final JTextField searchField;
    private final JLabel searchLabel;
    private final JButton searchButton;
    private final BookController bookController;
    private final List<JButton> buttons;
    private final List<JLabel> labels;
    private final JPanel searchLabelPanel;
    private final JPanel searchButtonPanel;
    private final String text;
    private final List<Book> currentSearchedBook;
    private final List<Book> allSearchedBooks;
    public SearchPage(BookController bookController, String text){
        super(605, 300, 560, 560);
        this.allBooks = bookController.getAllBooks();
        this.closeButton = new JButton();
        this.searchField = new JTextField();
        this.searchLabel = new JLabel();
        this.searchButton = new JButton();
        this.buttons = new ArrayList<>();
        this.bookController = bookController;
        this.labels = new ArrayList<>();
        this.searchLabelPanel = new JPanel();
        this.searchButtonPanel = new JPanel();
        this.currentSearchedBook = new ArrayList<>();
        this.allSearchedBooks = bookController.getSearchedBooks();
        this.text = text;
        setupComponents(text);
    }

    private void setupComponents(String text) {
        setupCloseButton();
        setupSearchedBook();
        setupSearchField();
        setupSearchLabel(text);
        setupSearchButtonPanel();
        setupSearchLabelPanel();
        setupButtonArray();
        setupSearchButton();
        setupBooksList(currentSearchedBook, buttons, labels, null, 100, 20, 10, 20);
    }

    private void setupCloseButton() {
        closeButton.setText("x");
        closeButton.setBounds(0,0, 20,20);
        closeButton.addActionListener(this);
        this.add(closeButton);
    }

    private void setupButtonArray() {
        for (int i = 0; i <= currentSearchedBook.size(); i++) {
            buttons.add(new JButton());
            buttons.get(i).setText("Rent");
            buttons.get(i).addActionListener(this);
        }
    }

    private void setupSearchButtonPanel() {
        searchButtonPanel.setBounds(355, 10, 80, 40);
        searchButtonPanel.setBackground(Color.ORANGE);
        searchButtonPanel.add(searchButton);
        this.add(searchButtonPanel);
    }

    private void setupSearchLabelPanel() {
        searchLabelPanel.setBounds(80, 10, 80, 40);
        searchLabelPanel.setBackground(Color.ORANGE);
        searchLabelPanel.add(searchLabel);
        this.add(searchLabelPanel);
    }

    private void setupSearchButton() {
        searchButton.setText("search");
        searchButton.addActionListener(this);
    }

    private void setupSearchField() {
        searchField.setBounds(150, 12, 200, 30);
        this.add(searchField);
    }

    private void setupSearchLabel(String text) {
        searchLabel.setText(text);
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    private void setupSearchedBook(){
        if (allSearchedBooks.isEmpty()){
            JLabel message = new JLabel("no previous searches");
            message.setBounds(200, 55, 150, 40);
            this.add(message);
        }else {
            Book lastBook = allSearchedBooks.get(Math.max(0, allSearchedBooks.size() - 1));

            switch (text) {
                case "title: " -> currentSearchedBook.add(lastBook);
                case "author: " -> {
                    for (Book book : allBooks) {
                        if (book.getAuthor().equals(lastBook.getAuthor())) {
                            currentSearchedBook.add(book);
                        }
                    }
                }
                case "genre: " -> {
                    for (Book book : allBooks) {
                        if (book.getGenre().equals(lastBook.getGenre())) {
                            currentSearchedBook.add(book);
                        }
                    }
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(searchButton)) {
                if (text.equals("title: ")){
                    bookController.addSearchedBook(searchField.getText());
                }
                if (text.equals("author: ")) {
                    for (Book book : allBooks) {
                        if (book.getAuthor().equals(searchField.getText())) {
                            bookController.addSearchedBook(book.getName());
                        }
                    }
                }
                if (text.equals("genre: ")) {
                    for (Book book : allBooks) {
                        if (book.getGenre().equals(searchField.getText())) {
                            bookController.addSearchedBook(book.getName());
                        }
                    }
            }
            navigateToPage(this, new SearchPage(bookController, text));
        } else if (source.equals(closeButton)) {
            this.dispose();
        }
        for (int i = 0; i < buttons.size(); i++) {
            if (source.equals(buttons.get(i))){
                bookController.rentBook(currentSearchedBook.get(i).getName());
                this.navigateToPage(this, new SearchPage(bookController,text));
            }
        }
    }
}
