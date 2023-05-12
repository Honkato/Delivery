package components;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    public Label(Color color, int x, int y, int w, int h){
        setBackground(color);
        setLocation(x,y);
        setSize(w,h);
    }
}
