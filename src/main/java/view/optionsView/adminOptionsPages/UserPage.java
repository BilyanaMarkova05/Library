package view.optionsView.adminOptionsPages;

import controller.AuthenticationImpl;
import controller.BookController;
import model.userModel.User;
import view.HomePage;
import view.optionsView.BaseOptionPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserPage extends BaseOptionPage implements ActionListener {
    private final BookController bookController;
    private final List<JLabel> labels;
    private final List<JButton> userButtons;
    private final List<JLabel> librarianLabels;
    private final List<JButton> librarianButtons;
    private final JPanel generalPanel;
    private final List<User> allUsers;
    private final List<User> allLibrarians;
    public UserPage(BookController bookController){
        setLayout(null);
        setupIcon("icon.png", 590,80, 100, 60 );
        setupTitleLabel("Library Management System", 30, 650, 90, 500, 50);
        this.bookController = bookController;
        this.labels = new ArrayList<>();
        this.userButtons = new ArrayList<>();
        this.librarianLabels = new ArrayList<>();
        this.librarianButtons = new ArrayList<>();
        this.generalPanel = new JPanel();
        this.allUsers = AuthenticationImpl.getInstance().getAllUsers("users");
        this.allLibrarians = AuthenticationImpl.getInstance().getAllUsers("librarians");
        this.getContentPane().setBackground(Color.ORANGE);
        setupComponents();
    }

    private void setupComponents() {
        setupButtonsArray();
        setupLibrarianButtonsArray();
        setupLabelsPanel("users: ", 650);
        setupLabelsPanel("librarians: ", 850);
        setupGeneralPanel();
    }

    private void setupGeneralPanel() {
        this.generalPanel.setBounds(500, 300, 700, 400);
        generalPanel.setBackground(Color.getHSBColor(54, 20, 197));
        JPanel userPanel = setupUsersList(allUsers, userButtons, labels, 0, 0, 0, 0);
        JPanel librarianPanel = setupUsersList(allLibrarians, librarianButtons, librarianLabels, 0, 0, 0, 0);

        generalPanel.add(userPanel);
        generalPanel.add(librarianPanel);
        this.add(generalPanel);
    }

    private void setupLabelsPanel(String text, int x) {
        JPanel labelsPanel = new JPanel();
        labelsPanel.setBounds(x, 250, 200, 50);
        labelsPanel.setBackground(Color.getHSBColor(54, 20, 197));
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        labelsPanel.add(label);
        this.add(labelsPanel);
    }


    private void setupLibrarianButtonsArray() {
        for (int i = 0; i < allLibrarians.size(); i++) {
            librarianButtons.add(new JButton());
            setupButton(librarianButtons.get(i));
        }
    }

    private void setupButtonsArray() {
        for (int i = 0; i < allUsers.size(); i++) {
            userButtons.add(new JButton());
            setupButton(userButtons.get(i));
        }
    }

    private void setupButton(JButton button) {
        button.setText("remove user");
        button.setSize(50, 20);
        button.setFocusable(false);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < userButtons.size(); i++) {
            if (source.equals(userButtons.get(i))){
                if (bookController.doesUserHaveBooks(allUsers.get(i).getName())){
                    JOptionPane.showMessageDialog(null, "This user have booked books. " +
                        "You cannot delete this account before the books are returned.");
                } else {
                    AuthenticationImpl.getInstance().removeProfile(allUsers.get(i).getName(), "users");
                }
                navigateToPage(this, new UserPage(bookController));
            }
        }

        for (int i = 0; i < librarianButtons.size(); i++) {
            if (source.equals(librarianButtons.get(i))){
                AuthenticationImpl.getInstance().removeProfile(allLibrarians.get(i).getName(), "librarians");
                navigateToPage(this, new UserPage(bookController));
            }
        }

        if (source.equals(this.getReturnButton())){
            navigateToPage(this, new AdminOptionPage(bookController));
        } else if (source.equals(this.getLogoutButton())){
            AuthenticationImpl.getInstance().logout();
            navigateToPage(this, new HomePage());
        }
    }
}
