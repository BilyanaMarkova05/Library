package view.optionsView;

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
    private final JButton addBookButton;
    private final BookController bookController;

    public AddBookPage(BookController bookController){
        this.bookController = bookController;
        this.bookTitleLabel = new JLabel();
        this.bookTitleField = new JTextField();
        this.bookGenreLabel = new JLabel();
        this.bookGenreTextField = new JTextField();
        this.addBookButton = new JButton();
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
        setupAddBookButton();
    }

    private void setupAddBookButton() {
        addBookButton.setText("Add book");
        addBookButton.setBounds(190, 230, 120, 40);
        this.add(addBookButton);
        addBookButton.addActionListener(this);
    }

    private void setupGenreField() {
        bookGenreTextField.setBounds(180, 160, 180, 40);
        this.add(bookGenreTextField);
    }

    private void setupGenreLabel() {
        bookGenreLabel.setText("Genre: ");
        bookGenreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bookGenreLabel.setBounds(110, 160, 70, 40);
        this.add(bookGenreLabel);
    }

    private void setupBookTitleField() {
        bookTitleField.setBounds(180, 100, 180, 40);
        this.add(bookTitleField);
    }

    private void setupBookTitleLabel() {
        bookTitleLabel.setText("Title: ");
        bookTitleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bookTitleLabel.setBounds(110, 100, 70, 40);
        this.add(bookTitleLabel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source.equals(addBookButton)){
            bookController.addBook(bookTitleField.getText(), "FREE", bookGenreTextField.getText());
            this.dispose();
            new LibOptionPage(bookController);
        }
    }
}
