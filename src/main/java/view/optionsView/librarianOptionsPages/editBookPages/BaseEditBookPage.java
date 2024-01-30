package view.optionsView.librarianOptionsPages.editBookPages;

import view.optionsView.BaseOptionPage;

import javax.swing.*;
import java.awt.*;

public class BaseEditBookPage extends BaseOptionPage {
    private final JLabel label;
    private final JTextField field;
    private final JButton button;
    private final JButton closeButton;
    private final JButton backButton;

    public BaseEditBookPage(JLabel label, JTextField field, JButton button){
        super(605, 300, 500, 500);
        this.getContentPane().setBackground(Color.getHSBColor(54, 20, 197));
        setLayout(null);
        this.label = label;
        this.field = field;
        this.button = button;
        this.closeButton = new JButton();
        this.backButton = new JButton();
        setupComponents();
    }

    private void setupComponents() {
        setupLabel();
        setupField();
        setupButton();
        setupCloseButton();
        setupBackButton();
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getButton() {
        return button;
    }

    public JTextField getField() {
        return field;
    }

    public JButton getBackButton() {
        return backButton;
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

    private void setupLabel() {
        label.setFont(new Font("Arial", Font.PLAIN, 22));
        label.setBounds(100,140, 100, 50);
        this.add(label);
    }

    private void setupField() {
        field.setBounds(190, 140, 200, 50);
        this.add(field);
    }

    private void setupButton() {
        button.setText("Save");
        button.setBounds(200, 200, 120, 50);
        button.addActionListener(this);
        this.add(button);
    }
}
