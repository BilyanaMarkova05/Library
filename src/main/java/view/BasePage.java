package view;

import javax.swing.*;
import java.awt.*;

public class BasePage extends JFrame {

    public BasePage(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void navigateToPage(JFrame currentFrame, JFrame jFrame){
        currentFrame.dispose();
        jFrame.setVisible(true);
    }

    public void setupIcon(String filename, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(filename);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(scaledIcon);

        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(Color.ORANGE);
        panel.add(iconLabel);
        this.add(panel);
    }
}
