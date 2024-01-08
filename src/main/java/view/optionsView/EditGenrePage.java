package view.optionsView;

import controller.BookController;
import model.bookModel.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGenrePage extends BaseEditBookPage implements ActionListener {
    private final Book book;
    private final BookController bookController;
    public EditGenrePage(Book book, BookController bookController){
        super(new JLabel("genre: "), new JTextField(), new JButton("Save"));
        this.book = book;
        this.bookController = bookController;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(getButton())){
            bookController.updateBookGenre(book.getName(), getField().getText());
            this.dispose();
        }  else if (source.equals(getCloseButton())) {
            navigateToPage(this, new LibrarianOptionPage(bookController));
        } else if (source.equals(getBackButton())) {
            navigateToPage(this, new EditBookPage(book, bookController));
        }
    }
}
