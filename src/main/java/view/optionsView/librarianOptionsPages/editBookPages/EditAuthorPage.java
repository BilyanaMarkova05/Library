package view.optionsView.librarianOptionsPages.editBookPages;

import controller.BookController;
import model.bookModel.Book;
import view.optionsView.librarianOptionsPages.LibrarianOptionPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EditAuthorPage extends BaseEditBookPage implements ActionListener {
    private final BookController bookController;
    private final Book book;
    public EditAuthorPage(Book book, BookController bookController){
        super(new JLabel("author: "), new JTextField(), new JButton("Save"));
        this.bookController = bookController;
        this.book = book;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(getButton())){
            bookController.updateBookAuthor(book.getName(), getField().getText());
            this.dispose();
        } else if (source.equals(getCloseButton())) {
            navigateToPage(this, new LibrarianOptionPage(bookController));
        } else if (source.equals(getBackButton())) {
            navigateToPage(this, new EditBookPage(book, bookController));
        }
    }
}
