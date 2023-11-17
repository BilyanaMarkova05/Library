package view.optionsView;

import controller.BookController;
import model.bookModel.Book;
import model.bookModel.BookStatus;
import view.BasePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BaseOptionPage extends BasePage implements ActionListener {
    private final JButton logoutButton;
    private final JButton returnButton;

    private BookController bookController;
    private final int y;
    public BaseOptionPage(BookController bookController) {
        this.setTitle("Options");
        this.bookController = bookController;
        this.logoutButton = new JButton();
        this.returnButton = new JButton();
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

    public void setupDeleteProfileButton(JButton deleteProfileButton) {
        deleteProfileButton.setText("Delete profile");
        deleteProfileButton.setBounds(1550, 45, 120, 20);
        deleteProfileButton.setFocusable(false);
        deleteProfileButton.addActionListener(this);
        this.add(deleteProfileButton);
    }

    public void setupAllBooksList(List<Book> bookList, List<JButton> buttons, List<JLabel> bookLabels) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.ORANGE);
        GridBagConstraints gbc = getGridBagConstraints();

        JPanel scrollablePanel = getScrollablePanel(bookList, buttons, bookLabels);

        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(scrollPane, gbc);
        this.add(mainPanel);
    }

    private static GridBagConstraints getGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(200, 600, 200, 600);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        return gbc;
    }

    private JPanel getScrollablePanel(List<Book> bookList, List<JButton> buttons, List<JLabel> bookLabels) {
        JPanel scrollablePanel = new JPanel();
        scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS));
        setupAllBooksLabelArray(bookList, bookLabels);
        for (int i = 0; i < bookList.size(); i++) {
            JPanel linePanel = new JPanel();
            linePanel.add(bookLabels.get(i));
            if (bookList.get(i).getBookStatus() == BookStatus.FREE) {
                setupIcon("green tick icon.png", 10, 10, 15, 15, linePanel);
            }else{
                setupIcon("red cross icon.png", 10, 10, 15, 15, linePanel);
            }

            if(buttons != null){
            linePanel.add(buttons.get(i));
            }
            scrollablePanel.add(linePanel);
        }
        return scrollablePanel;
    }

    private void setupAllBooksLabelArray(List<Book> bookList, List<JLabel> bookLabels){
        for (int i = 0; i < bookList.size(); i++) {
            bookLabels.add(new JLabel(bookList.get(i).getName() + " - " + bookList.get(i).getGenre()));
            bookLabels.get(i).setFont(new Font("Arial", Font.PLAIN, 20));
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
