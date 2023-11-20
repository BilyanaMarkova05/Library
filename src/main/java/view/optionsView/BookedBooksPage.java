package view.optionsView;

import controller.BookController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class BookedBooksPage extends BaseOptionPage implements ActionListener {
    private List<JLabel> bookedBooks;
    private JButton closeButton;
    public BookedBooksPage(BookController bookController){
        super(bookController, 605, 300, 500, 500);
        this.closeButton = new JButton();
        this.bookedBooks = new ArrayList<>();
        setupCloseButton();
        this.add(setupBooksList(bookController.getBookedBooks(), bookedBooks, 23, 23,
                23, 23));
    }

    private void setupCloseButton(){
        closeButton.setText("x");
        closeButton.setBounds(7,7,15,15);
        closeButton.setFocusable(true);
        closeButton.addActionListener(this);
        this.add(closeButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(closeButton)){
            this.dispose();
        }
    }
}
