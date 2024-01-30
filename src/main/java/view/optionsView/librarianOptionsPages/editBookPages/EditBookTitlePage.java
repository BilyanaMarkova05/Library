package view.optionsView.librarianOptionsPages.editBookPages;

import controller.BookController;
import model.bookModel.Book;
import view.optionsView.librarianOptionsPages.LibrarianOptionPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBookTitlePage extends BaseEditBookPage implements ActionListener {
    private final BookController bookController;
    private final Book book;

    public EditBookTitlePage(Book book, BookController bookController){
        super(new JLabel("new title: "), new JTextField(), new JButton("Save"));
        this.bookController = bookController;
        this.book = book;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(getCloseButton())){
            navigateToPage(this, LibrarianOptionPage.getInstance(bookController));
        } else if (source.equals(getBackButton())) {
            navigateToPage(this, EditBookPage.getInstance(book, bookController));
        } else if (source.equals(getButton())) {
            bookController.updateBookTitle(book.getName(), getField().getText());
            this.dispose();
        }
    }
}
