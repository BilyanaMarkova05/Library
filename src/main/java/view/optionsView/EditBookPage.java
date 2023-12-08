package view.optionsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBookPage extends BaseOptionPage implements ActionListener {
    private final JButton editBookTitleButton;
    private final JButton editAuthorButton;
    private final JButton editGenreButton;
    private final JButton editNumberButton;
    private final JButton closeButton;

    public EditBookPage (){
        super(605, 300, 500, 500);
        setLayout(null);
        this.getContentPane().setBackground(Color.getHSBColor(54, 20, 197));
        this.editBookTitleButton = new JButton();
        this.editAuthorButton = new JButton();
        this.editGenreButton = new JButton();
        this.editNumberButton = new JButton();
        this.closeButton = new JButton();
        setupComponents();
    }

    private void setupComponents() {
        setupButton(editBookTitleButton, "Edit title", 90);
        setupButton(editAuthorButton, "Edit author", 150);
        setupButton(editGenreButton, "Edit genre", 210);
        setupButton(editNumberButton, "Edit number", 270);
        setCloseButton();
    }

    private void setCloseButton() {
        closeButton.setText("x");
        closeButton.setBounds(2, 2, 20,20);
        closeButton.addActionListener(this);
        this.add(closeButton);
    }

    private void setupButton(JButton button, String text, int y) {
        button.setText(text);
        button.setBounds(180, y, 150, 50);
        button.addActionListener(this);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(closeButton)){
            this.dispose();
        }
    }
}
