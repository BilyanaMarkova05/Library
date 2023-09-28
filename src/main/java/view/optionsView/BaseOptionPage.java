package view.optionsView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import model.bookModel.Book;
import view.authenticationView.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BaseOptionPage extends JFrame implements ActionListener {
    private final JButton logoutButton;
    private final JButton returnButton;
    private final BookController bookController;
    private final List<JLabel> allBooks;
    private int y;

    public BaseOptionPage(BookController bookController) {
        this.logoutButton = new JButton();
        this.returnButton = new JButton();
        this.bookController = bookController;
        this.allBooks = new ArrayList<>();
        this.y = 50;
        setupComponents();
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

    private void setupComponents() {
        setupAllBooks();
        setupLogoutButton();
        setupReturnButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(logoutButton);
        this.add(returnButton);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Options");
        this.setBounds(550, 150, 420, 630);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupReturnButton() {
        returnButton.setText("return");
        returnButton.setBounds(10, 20, 90, 20);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupLogoutButton() {
        logoutButton.setText("Logout");
        logoutButton.setBounds(300, 20, 90, 20);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
    }

    private void setupAllBooks() {
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