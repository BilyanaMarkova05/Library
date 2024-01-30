package view.optionsView.librarianOptionsPages;

import controller.BookController;
import view.optionsView.BaseOptionPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class BookedBooksPage extends BaseOptionPage implements ActionListener {
    private final JButton closeButton;

    public BookedBooksPage(BookController bookController){
        super(605, 300, 500, 500);
        this.closeButton = new JButton();
        setupCloseButton();
        List<JLabel> bookedBooks = new ArrayList<>();
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
