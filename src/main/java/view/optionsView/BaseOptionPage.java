package view.optionsView;

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
    private final int y;
    public BaseOptionPage() {
        this.setTitle("Options");
        this.logoutButton = new JButton();
        this.returnButton = new JButton();
        this.y = 50;
        setupLogoutButton();
        setupReturnButton();
    }

    public BaseOptionPage(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.logoutButton = new JButton();
        this.returnButton = new JButton();
        this.y = 50;
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

    //setupAllBooks
    public void setupBooksList(List<Book> bookList, List<JButton> buttons, List<JLabel> bookLabels) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.ORANGE);
        GridBagConstraints gbc = getGridBagConstraints(200, 600, 200, 600);

        JPanel scrollablePanel = getScrollablePanel(bookList, buttons, bookLabels);

        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(scrollPane, gbc);
        this.add(mainPanel);
    }


    private static GridBagConstraints getGridBagConstraints(int top, int left, int bottom, int right) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(top, left, bottom, right);
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


    //setupBookedBooks
    public JPanel setupBooksList(List<String> bookList, List<JLabel> bookLabels, int top,
                               int left, int bottom, int right) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = getGridBagConstraints(top, left, bottom, right);

        JPanel scrollablePanel = getScrollablePanel(bookList, bookLabels);

        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(scrollPane, gbc);
        return mainPanel;
    }
    private JPanel getScrollablePanel(List<String> bookList, List<JLabel> bookLabels) {
        JPanel scrollablePanel = new JPanel();
        scrollablePanel.setBackground(Color.getHSBColor(54, 20, 197));
        scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS));
        setupBookedBooksLabelArray(bookList, bookLabels);
        for (int i = 0; i < bookList.size(); i++) {
            scrollablePanel.add(bookLabels.get(i));
        }
        return scrollablePanel;
    }


    private void setupBookedBooksLabelArray(List<String> bookList, List<JLabel> bookLabels){
        for (int i = 0; i < bookList.size(); i++) {
            bookLabels.add(new JLabel("                       " + bookList.get(i)));
            bookLabels.get(i).setFont(new Font("Arial", Font.PLAIN, 20));
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
