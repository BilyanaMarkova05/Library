package view.optionsView;

import controller.BookController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBookPage extends BaseOptionPage implements ActionListener {
    private final JButton editBookTitleButton;
    private final JButton editAuthorButton;
    private final JButton editGenreButton;
    private final JButton editNumberButton;
    private final JButton closeButton;
    private final BookController bookController;
    private static EditBookPage instance;

    public EditBookPage (BookController bookController){
        super(605, 300, 500, 500);
        setLayout(null);
        this.getContentPane().setBackground(Color.getHSBColor(54, 20, 197));
        this.editBookTitleButton = new JButton();
        this.editAuthorButton = new JButton();
        this.editGenreButton = new JButton();
        this.editNumberButton = new JButton();
        this.closeButton = new JButton();
        this.bookController = bookController;
        setupComponents();
    }

    public static EditBookPage getInstance(BookController bookController) {
        if (instance == null) {
            instance = new EditBookPage(bookController);
        }
        return instance;
    }

    private void setupComponents() {
        setupButton(editBookTitleButton, "Edit title", 90);
        setupButton(editAuthorButton, "Edit author", 150);
        setupButton(editGenreButton, "Edit genre", 210);
        setupButton(editNumberButton, "Edit number", 270);
        setCloseButton();
    }

    private void setCloseButton() {
        closeButton.setText("x");
        closeButton.setBounds(4, 4, 20,20);
        closeButton.addActionListener(this);
        this.add(closeButton);
    }

    private void setupButton(JButton button, String text, int y) {
        button.setText(text);
        button.setBounds(180, y, 150, 50);
        button.addActionListener(this);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(closeButton)){
            this.dispose();
        } else if (source.equals(editBookTitleButton)) {
            new EditBookTitlePage(bookController);
        } else if (source.equals(editNumberButton)){
            new EditBookNumber(bookController);
        }
    }
}
