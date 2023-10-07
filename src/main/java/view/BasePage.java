package view;

import javax.swing.*;
import java.awt.*;

public class BasePage extends JFrame {

    public BasePage(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    public void navigateToPage(JFrame jFrame){
        this.dispose();
        jFrame.setVisible(true);
    }
}
