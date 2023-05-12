package screens;

import components.Button;
import entities.Restaurant;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainMenu extends JPanel  {
    User user;
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    DefaultListModel<Restaurant> rests = new DefaultListModel<>();
    JList<Restaurant> lista = new JList<>(rests);

    public MainMenu(ArrayList<Restaurant> restaurants){

        this.restaurants = restaurants;
        refresh();
        Button refresh = new Button("refresh", 0, 500, 100, 50);
        refresh.addActionListener(e->{refresh();});
        add(refresh);
        lista.setSize(200, 400);
        lista.setLocation(0,100);
        add(lista);
        setBackground(Color.BLUE);
        setLayout(null);
        setVisible(true);
    }
    private void refresh(){
        for (Restaurant rest: restaurants) {
            System.out.println("0");
            System.out.println(rest.getName());
            if (restaurants.contains(rest)){
                rests.addElement(rest);
            }
        }
    }
}
