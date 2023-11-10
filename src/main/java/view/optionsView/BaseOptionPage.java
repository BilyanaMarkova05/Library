package view.optionsView;

import controller.BookController;
import view.BasePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BaseOptionPage extends BasePage implements ActionListener {
    private final JButton logoutButton;
    private final JButton returnButton;
    private final BookController bookController;
    private final List<JLabel> allBooks;
    private int y;
    public BaseOptionPage(BookController bookController) {
        this.setTitle("Options");
        this.logoutButton = new JButton();
        this.returnButton = new JButton();
        this.bookController = bookController;
        this.allBooks = new ArrayList<>();
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

    public void setupAllBooksList(List<JButton> buttons) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.ORANGE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(250, 600, 150, 600);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JPanel scrollablePanel = new JPanel();
        scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS));
        setupAllBooksLabelArray();
        for (int i = 0; i < bookController.getAllBooks().size(); i++) {
            JPanel linePanel = new JPanel();
            linePanel.add(allBooks.get(i));
            linePanel.add(buttons.get(i));
            scrollablePanel.add(linePanel);
        }

        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(scrollPane, gbc);
        this.add(mainPanel);
    }

    private void setupAllBooksLabelArray(){
        for (int i = 0; i < bookController.getAllBooks().size(); i++) {
            allBooks.add(new JLabel(bookController.getAllBooks().get(i).getName()));
            allBooks.get(i).setFont(new Font("Arial", Font.PLAIN, 20));
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
