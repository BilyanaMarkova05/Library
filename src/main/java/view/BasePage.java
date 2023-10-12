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
}
