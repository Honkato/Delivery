package screens;

import entities.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RegisterFood extends JPanel {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    public RegisterFood(){
        setBackground(Color.GREEN);
        setLayout(null);
        setVisible(true);
    }
}
