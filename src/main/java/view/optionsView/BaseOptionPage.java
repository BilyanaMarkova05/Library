package view.optionsView;

import controller.BookController;
import model.bookModel.Book;
import view.BasePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BaseOptionPage extends BasePage implements ActionListener {
    private final JButton logoutButton;
    private final JButton returnButton;
    private final BookController bookController;
    private final List<JLabel> allBooks;
    private int y;

    public BaseOptionPage(BookController bookController) {
        this.getContentPane().setBackground(Color.ORANGE);
        this.setTitle("Options");
        setLayout(null);
        this.logoutButton = new JButton();
        this.returnButton = new JButton();
        this.bookController = bookController;
        this.allBooks = new ArrayList<>();
        this.y = 50;
        setupLogoutButton();
        setupReturnButton();
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    @Override
    public int getY() {
        return y;
    }


    private void setupReturnButton() {
        returnButton.setText("<");
        returnButton.setBounds(10, 20, 20, 20);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
        this.add(returnButton);
    }

    private void setupLogoutButton() {
        logoutButton.setText("Logout");
        logoutButton.setBounds(1550, 20, 90, 20);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
        this.add(logoutButton);
    }

    public void setupAllBooks() {
        List<Book> allBooksDb = bookController.getAllBooks();
        for (int i = 0; i < allBooksDb.size(); i++) {
            allBooks.add(new JLabel());
            allBooks.get(i).setText(allBooksDb.get(i).getName() + " " + allBooksDb.get(i).getBookStatus() + " " +
                    allBooksDb.get(i).getGenre());
            allBooks.get(i).setBounds(140, y, 190, 60);
            this.add(allBooks.get(i));
            y+= 30;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
