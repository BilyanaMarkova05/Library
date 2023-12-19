package view.optionsView;

import controller.BookController;

import java.awt.*;
import java.awt.event.ActionListener;

public class EditBookNumber extends BaseOptionPage implements ActionListener {

    public EditBookNumber(BookController bookController){
        super(605, 300, 500, 500);
        setLayout(null);
        this.getContentPane().setBackground(Color.getHSBColor(54, 20, 197));
    }
}
