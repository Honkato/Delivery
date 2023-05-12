package screens;

import components.Button;
import entities.Pedido;
import entities.Restaurant;
import entities.User;

import javax.swing.*;
import java.util.ArrayList;

public class Aplicativo extends JFrame{
    JFrame frame = new JFrame();
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    int idRes = 0;
    ArrayList<User> users = new ArrayList<>();
    int idUser = 0;
    ArrayList<Pedido> pedidos = new ArrayList<>();
    int idPed = 0;

    Login login = new Login(users);
    MainMenu menu = new MainMenu(restaurants);

    Button logar = new Button("login");
    Button deslogar = new Button("deslogin");
    public Aplicativo(String title){
        //------------------------ PRESETS
        super(title);
        setContentPane(login);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,800);
        setLocationRelativeTo(null);
        setLayout(null);
        //------------------------ PRESETS //
        //------------------------  COMPONENTS
        //login
        logar.setBounds(100, 300, 100, 50);

        //home
        deslogar.setBounds(10,10,100,50);

        //------------------------  COMPONENTS //
        //------------------------ ADD
        addAllUsers();
        addAllRestaurants();
        addRoutes();
        //LOGIN
        login.add(logar);
        //HOME
        menu.add(deslogar);
        //------------------------ ADD //

        setVisible(true);
    }

    public void cadastrarRestaurante(String nome, int x, int y){
            restaurants.add(new Restaurant(idRes, nome, x,y));
            idRes += 1;
    }
    public void cadastrarUsers(String nome, String CPF, int x, int y){
        users.add(new User(idUser, nome, CPF, x,y));
        idUser += 1;
    }


    private void addRoutes(){
        // LOGIN -> HOME
        logar.addActionListener(e -> {
            if(!login.logar()){
                return;
            }
            login.setVisible(false);
            setContentPane(menu);
            menu.setVisible(true);
        });
        // HOME -> LOGIN
        deslogar.addActionListener(e ->{
            menu.setVisible(false);
            setContentPane(login);
            login.setVisible(true);
        });
    }
    private void addAllUsers(){
        cadastrarUsers("Gustavo", "83", 50,50);
        cadastrarUsers("", "", 50,50);
    }
    private void addAllRestaurants(){
        cadastrarRestaurante("Firelink Shrimp",0,0);
        cadastrarRestaurante("Solaire Soup",10,0);
    }

}
