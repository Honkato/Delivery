package application;

import components.Button;
import components.ErrorPopUp;
import configs.CadFoods;
import configs.CadRes;
import entities.Pedido;
import entities.Restaurant;
import entities.Role;
import entities.User;
import screens.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class Aplicativo extends JFrame {
    // LISTS
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Pedido> pedidos = new ArrayList<>();

    User loggedUser;
    // SCREENS
    CadRes cadRes = new CadRes();
    CadFoods cadFoods = new CadFoods();
    Login login = new Login(users);
    MainMenu menu = new MainMenu(restaurants, pedidos);
    ShoppingCart cart = new ShoppingCart(restaurants, pedidos);
    Register register = new Register();
    Quietus quietus = new Quietus();

    // COMPONENTS
    Button logar = new Button("login");
    Button voltarLogar = new Button("voltar");
    Button registar = new Button("registar");
    Button registerUser = new Button("Register User");
    Button deslogar = new Button("deslogin");
    Button carrinho = new Button("carrinho");
    Button comprar = new Button("finalizar");
    Button voltar = new Button("Voltar");
    Button ok = new Button("OK");
    // IDS
    int idRes = 0;
    int idUser = 0;
    int idPed = 0;


    public Aplicativo(String title){
        //------------------------ PRESETS
        super(title);
        setContentPane(login);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,800);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        //------------------------ PRESETS //
        //------------------------  COMPONENTS
        //register
        voltarLogar.setBounds(10, 10 , 100, 50);
        registerUser.setBounds(200, 400, 100, 50);
        //login
        logar.setBounds(100, 300, 100, 50);
        registar.setBounds(200, 300,100,50);
        //home
        deslogar.setBounds(10,10,100,50);
        carrinho.setBounds(375,10,100,50);

        //cart
        comprar.setBounds(380, 700, 100, 50);
        voltar.setBounds(10, 10, 100, 50);
        //QUIETUS
        ok.setBounds(380, 700, 100, 50);
        //------------------------  COMPONENTS //
        //------------------------ ADD
        addAllUsers();
        addAllRestaurants();
        addAllFoods();

        addRoutes();
        //REGISTER
        register.add(voltarLogar);
        register.add(registerUser);
        //LOGIN
        login.add(logar);
        login.add(registar);
        //HOME
        menu.add(deslogar);
        menu.add(carrinho);

        //CART
        cart.add(voltar);
        cart.add(comprar);
        //QUIETUS
        quietus.add(ok);
        //------------------------ ADD //

        setVisible(true);
    }
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (c == ' '){
                continue;
            }
            if(!Character.isLetter(c)) {
                System.out.println(c);
                return false;
            }
        }

        return true;
    }
    public void cadastrarRestaurante(String[] allInOne){
        cadastrarRestaurante(allInOne[0], Integer.parseInt(allInOne[1]), Integer.parseInt(allInOne[2]));
    }

    public void cadastrarRestaurante(String nome, int x, int y){
        for (Restaurant rest : restaurants){
            if (Objects.equals(nome, rest.getName()) || !isAlpha(nome)){
                System.out.println(nome);
                new ErrorPopUp("ATENTITON","It already has a restaurant with this name, or is invalid");
                return;
            }
        }
        restaurants.add(new Restaurant(idRes, nome, x,y));
        idRes += 1;
    }
    public void cadastrarUsers(String nome, String CPF, int x, int y, Role role){
        for (User user : users){
            if (Objects.equals(nome, user.getName()) || !isAlpha(nome)){
                System.out.println(nome);
                new ErrorPopUp("ATENTITON","It already has a User with this name, or is invalid");
                return;
            }
        }
        if (register.admLogin()){
            users.add(new User(idUser, nome, CPF, x,y, role));
        }else{
            users.add(new User(idUser, nome, CPF, x,y));
        }
        idUser += 1;
    }
    public void cadastrarUsers(String nome, String CPF, int x, int y){
        for (User user : users){
            if (Objects.equals(nome, user.getName()) || !isAlpha(nome)){
                System.out.println(nome);
                new ErrorPopUp("ATENTITON","It already has a User with this name, or is invalid");
                return;
            }
        }

        users.add(new User(idUser, nome, CPF, x,y));

        idUser += 1;
    }


    private void addRoutes(){
        // ACTUALLY AN ACTION
        registerUser.addActionListener(e ->{
            cadastrarUsers(register.getNome(),register.getCpf(),Integer.parseInt(register.getXL()),Integer.parseInt(register.getYL()), register.getRole());
        });
        // LOGIN -> REGISTER
        registar.addActionListener(e -> {
            login.logar();
            login.setVisible(false);
            setContentPane(register);
            register.setUser(login.getLoggedUser());
            register.setVisible(true);
        });
        // REGISTER -> LOGIN
        voltarLogar.addActionListener(e -> {
            register.setVisible(false);
            setContentPane(login);
            login.setVisible(true);
        });
        // LOGIN -> HOME
        logar.addActionListener(e -> {
            if(!login.logar()){
                return;
            }
            login.setVisible(false);
            setContentPane(menu);
            menu.setUser(login.getLoggedUser());
            cart.setUser(login.getLoggedUser());
            menu.setVisible(true);
            menu.refresh();
        });
        // HOME -> LOGIN
        deslogar.addActionListener(e ->{
            menu.setVisible(false);
            setContentPane(login);
            login.setVisible(true);
        });
        // HOME -> CART
        carrinho.addActionListener(e ->{
            menu.setVisible(false);
            menu.setDefaultPrices();
            setContentPane(cart);
            cart.updateRequests();
            cart.updatePrice();
            cart.setVisible(true);
        });
        // CART -> QUIETUS
        comprar.addActionListener(e -> {
            if (cart.getModelRequests().isEmpty()){
                return;
            }
            quietus.setModelQuietus(cart.getModelRequests());
            cart.setVisible(false);
            setContentPane(quietus);
            quietus.refresh();
            quietus.setVisible(true);
        });
        // CART -> HOME
        voltar.addActionListener(e ->{

            cart.setVisible(false);
            setContentPane(menu);
            menu.updateRequests();
            menu.updatePrice();
            menu.setVisible(true);
        });
        // QUIETUS -> CART
        ok.addActionListener(e->{
            cart.finishShopping();
            quietus.setVisible(false);
            setContentPane(cart);
            cart.setVisible(true);
        });

    }
    private void addAllUsers(){
        users.add(new User(idUser, "", "", 10,15, Role.ADM));
        idUser++;
        cadastrarUsers("Gustavo", "83", 50,50);
    }
    private void addAllRestaurants(){
        cadastrarRestaurante(cadRes.FirelinkShrimp());
        cadastrarRestaurante(cadRes.SolaireSoup());
        cadastrarRestaurante(cadRes.JohnGourmet());
        cadastrarRestaurante(cadRes.Dadora());

    }
    private void addAllFoods(){
        cadFoods.firelinkShrimp(restaurants.get(0));
        cadFoods.solaireSoup(restaurants.get(1));
        cadFoods.johnGourmet(restaurants.get(2));
        cadFoods.dadora(restaurants.get(3));
    }
}
