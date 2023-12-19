package view.optionsView;

import controller.BookController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditBookTitlePage extends BaseOptionPage implements ActionListener {
    private final JLabel currentTitleLabel;
    private final JTextField currentTitleField;
    private final JLabel newTitleLabel;
    private final JTextField newTitleField;
    private final JButton saveButton;
    private final JButton closeButton;
    private final JButton backButton;
    private final BookController bookController;
    private final LibrarianOptionPage librarianOptionPage;

    public EditBookTitlePage(BookController bookController){
        super(605, 300, 500, 500);
        setLayout(null);
        this.getContentPane().setBackground(Color.getHSBColor(54, 20, 197));
        this.currentTitleLabel = new JLabel();
        this.currentTitleField = new JTextField();
        this.newTitleLabel = new JLabel();
        this.newTitleField = new JTextField();
        this.saveButton = new JButton();
        this.closeButton = new JButton();
        this.backButton = new JButton();
        this.bookController = bookController;
        this.librarianOptionPage = new LibrarianOptionPage(bookController);
        setupComponents();
    }

    private void setupComponents() {
        setupCurrentTitleLabel();
        setupCurrentTitleField();
        setupNewTitleLabel();
        setupNewTitleField();
        setupSaveButton();
        setupCloseButton();
        setupBackButton();
    }

    private void setupCurrentTitleField() {
        currentTitleField.setBounds(200, 100, 200, 50);
        this.add(currentTitleField);
    }

    private void setupCurrentTitleLabel() {
        currentTitleLabel.setText("current title: ");
        currentTitleLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        currentTitleLabel.setBounds(80, 100, 200, 50);
        this.add(currentTitleLabel);
    }

    private void setupBackButton() {
        backButton.setText("<");
        backButton.setBounds(30, 4, 20, 20);
        backButton.addActionListener(this);
        this.add(backButton);
    }

    private void setupCloseButton() {
        closeButton.setText("x");
        closeButton.setBounds(4,4, 20, 20);
        closeButton.addActionListener(this);
        this.add(closeButton);
    }

    private void setupNewTitleLabel() {
        newTitleLabel.setText("new title: ");
        newTitleLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        newTitleLabel.setBounds(100,160, 100, 50);
        this.add(newTitleLabel);
    }

    private void setupNewTitleField() {
        newTitleField.setBounds(200, 160, 200, 50);
        this.add(newTitleField);
    }

    private void setupSaveButton() {
        saveButton.setText("Save");
        saveButton.setBounds(200, 220, 120, 50);
        saveButton.addActionListener(this);
        this.add(saveButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<JButton> editButtons = librarianOptionPage.getEditButtons();
        Object source = e.getSource();
        if (source.equals(closeButton)){
            navigateToPage(this, LibrarianOptionPage.getInstance(bookController));
        } else if (source.equals(backButton)) {
            navigateToPage(this, EditBookPage.getInstance(bookController));
        } else if (source.equals(saveButton)) {
            for (int i = 0; i < editButtons.size(); i++) {
                bookController.updateBookTitle(currentTitleField.getText(), newTitleField.getText());
            }
        }
    }
}
