package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public boolean inverter = false;
    public Button(String name){
        super(name);
    };

    public Button(String name, int x, int y, int w, int h){
        super(name);
        setLocation(x,y);
        setSize(w,h);
    }
    public Button(String name, int x, int y, int w, int h, Color colorBG, Color colorFG){
        super(name);
        setLocation(x,y);
        setSize(w,h);
        setBackground(colorBG);
        setForeground(colorFG);
    }

}
