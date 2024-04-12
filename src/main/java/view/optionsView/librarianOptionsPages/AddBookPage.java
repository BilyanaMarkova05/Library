package view.optionsView.librarianOptionsPages;

import controller.BookController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookPage extends JFrame implements ActionListener {
    private final JLabel bookTitleLabel;
    private final JTextField bookTitleField;
    private final JLabel bookGenreLabel;
    private final JTextField bookGenreTextField;
    private final JLabel bookAuthorLabel;
    private final JTextField bookAuthorField;
    private final JLabel numberLabel;
    private final JTextField numberField;
    private final JButton addBookButton;
    private final BookController bookController;
    private final JButton closeButton;

    public AddBookPage(BookController bookController){
        this.bookController = bookController;
        this.bookTitleLabel = new JLabel();
        this.bookTitleField = new JTextField();
        this.bookGenreLabel = new JLabel();
        this.bookGenreTextField = new JTextField();
        this.bookAuthorLabel = new JLabel();
        this.bookAuthorField = new JTextField();
        this.numberLabel = new JLabel();
        this.numberField = new JTextField();
        this.addBookButton = new JButton();
        this.closeButton = new JButton();
        setupFrame();
    }

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(605, 300, 500, 500);
        getContentPane().setBackground(Color.getHSBColor(54, 20, 197));
        setupComponents();
        setVisible(true);
    }

    private void setupComponents() {
        setupBookTitleLabel();
        setupBookTitleField();
        setupGenreLabel();
        setupGenreField();
        setupBookAuthorLabel();
        setupBookAuthorField();
        setupNumberLabel();
        setupNumberField();
        setupAddBookButton();
        setupReturnButton();
    }

    private void setupNumberField() {
        numberField.setBounds(270, 160, 40, 40);
        this.add(numberField);
    }

    private void setupNumberLabel() {
        numberLabel.setText("Number: ");
        numberLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        numberLabel.setBounds(190, 160, 90, 40);
        this.add(numberLabel);
    }

    private void setupBookAuthorField() {
        bookAuthorField.setBounds(180, 120, 180, 40);
        this.add(bookAuthorField);
    }

    private void setupBookAuthorLabel() {
        bookAuthorLabel.setText("Author: ");
        bookAuthorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bookAuthorLabel.setBounds(110, 120, 80, 40);
        this.add(bookAuthorLabel);
    }

    private void setupReturnButton() {
        closeButton.setText("x");
        closeButton.setBounds(7,7,15,15);
        closeButton.setFocusable(true);
        closeButton.addActionListener(this);
        this.add(closeButton);
    }

    private void setupAddBookButton() {
        addBookButton.setText("Add book");
        addBookButton.setBounds(190, 230, 120, 40);
        this.add(addBookButton);
        addBookButton.addActionListener(this);
    }

    private void setupGenreField() {
        bookGenreTextField.setBounds(180, 80, 180, 40);
        this.add(bookGenreTextField);
    }

    private void setupGenreLabel() {
        bookGenreLabel.setText("Genre: ");
        bookGenreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bookGenreLabel.setBounds(110, 80, 70, 40);
        this.add(bookGenreLabel);
    }

    private void setupBookTitleField() {
        bookTitleField.setBounds(180, 40, 180, 40);
        this.add(bookTitleField);
    }

    private void setupBookTitleLabel() {
        bookTitleLabel.setText("Title: ");
        bookTitleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bookTitleLabel.setBounds(110, 40, 70, 40);
        this.add(bookTitleLabel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source.equals(addBookButton)){
            bookController.addBook(bookTitleField.getText(), "FREE", bookGenreTextField.getText(),
                    bookAuthorField.getText(), Integer.parseInt(numberField.getText()));
            this.dispose();
            new LibrarianOptionPage(bookController);
        } else if (source.equals(closeButton)) {
            this.dispose();
            //new LibrarianOptionPage(bookController);
        }
    }
}
