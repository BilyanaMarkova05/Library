package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends BasePage implements ActionListener {
    private JButton button;
    private JLabel title;
    public Test() {
        this.getContentPane().setBackground(Color.ORANGE);
        this.button = new JButton();
        this.title = new JLabel();
        setupButton();
        setupLabel();
    }

    private void setupLabel() {
        setLayout(null);
        title.setText("text");
        title.setBounds(100,100,100,100);
        add(title);
    }

    private void setupButton() {
        this.setLayout(null);
        button.setBounds(200, 50, 120, 30);
        button.setText("button");
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

//    private void alignButtonsInMainPanel(JPanel buttonPanel, JPanel scoreBtnPanel) {
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        mainPanel.add(Box.createVerticalGlue());
//        mainPanel.add(buttonPanel);
//        mainPanel.add(Box.createVerticalStrut(0));
//        scoreBtnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        mainPanel.add(scoreBtnPanel);
//        mainPanel.add(Box.createVerticalGlue());
//    }
}
