package view.optionsView.librarianOptionsPages.editBookPages;

import controller.BookController;
import model.bookModel.Book;
import view.optionsView.librarianOptionsPages.LibrarianOptionPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBookNumber extends BaseEditBookPage implements ActionListener {
    private final Book book;
    private final BookController bookController;
    public EditBookNumber(Book book, BookController bookController){
        super(new JLabel("number: "), new JTextField(), new JButton("Save"));
        this.book = book;
        this.bookController = bookController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(getButton())){
            bookController.updateBookNumber(book, Integer.parseInt(getField().getText()));
            this.dispose();
        } else if (source.equals(getBackButton())) {
            navigateToPage(this, new EditBookPage(book, bookController));
        } else if (source.equals(getCloseButton())) {
            navigateToPage(this, new LibrarianOptionPage(bookController));
        }
    }
}
