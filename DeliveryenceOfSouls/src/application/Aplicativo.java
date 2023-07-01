package application;

import components.Button;
import components.ErrorPopUp;
import components.SuccessPopUp;
import configs.CadFoods;
import configs.CadRes;
import entities.Pedido;
import entities.Restaurant;
import entities.Role;
import entities.User;
import screens.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Aplicativo extends JFrame {
    // LISTS
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Pedido> pedidos = new ArrayList<>();
    CadRes cadRes = new CadRes();
    CadFoods cadFoods = new CadFoods();
    // SCREENS
    Login login = new Login(users, new Color(0xB71E44));
    Shopping shopping = new Shopping(restaurants, pedidos, new Color(0x8F3060));
    ShoppingCart cart = new ShoppingCart(restaurants, pedidos, new Color(0x71379F));
    RegisterUser registerUser = new RegisterUser(new Color(0xB71E44));
    Quietus quietus = new Quietus(new Color(0xE5B678));
    RegisterRest registerRest = new RegisterRest(restaurants, new Color(0xB61C1C));
    RegisterFood registerFood = new RegisterFood(restaurants, new Color(0x8C1D1D));

    // COMPONENTS
    Button logar = new Button("login");
    Button voltarLogar = new Button("voltar");
    Button voltarResLogar = new Button("voltar");
    Button voltarFoodLogar = new Button("voltar");
    Button registerU = new Button("registar");
    Button registerR = new Button("registar restaurante");
    Button registerF = new Button("registar comida");
    Button registerUserButton = new Button("Register User");
    Button registerRestButton = new Button("Register Restaurant");
    Button registerFoodButton = new Button("Register Food");
    Button deslogar = new Button("deslogin");
    Button carrinho = new Button("carrinho");
    Button comprar = new Button("finalizar");
    Button voltar = new Button("Voltar");
    Button ok = new Button("OK");

    // IDS
    int idRes = 0;
    int idUser = 0;
    int idPed = 0;
    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

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
        registerUserButton.setBounds(200, 400, 100, 50);

        //login
        logar.setBounds(100, 300, 100, 50);
        registerU.setBounds(300, 300,100,50);
        registerR.setBounds(100, 400, 150, 50);
        registerF.setBounds(250, 400, 150, 50);

        //SHOPPING
        deslogar.setBounds(10,10,100,50);
        carrinho.setBounds(375,10,100,50);

        //cart
        comprar.setBounds(380, 700, 100, 50);
        voltar.setBounds(10, 10, 100, 50);

        //QUIETUS
        ok.setBounds(380, 700, 100, 50);

        //REGISTER RESTAURANT
        registerRestButton.setBounds(100, 300, 300, 50);
        voltarResLogar.setBounds(10, 10 , 100, 50);

        //REGISTER COMIDA
        registerFoodButton.setBounds(100, 300, 300, 50);
        voltarFoodLogar.setBounds(10, 10 , 100, 50);
        //------------------------  COMPONENTS //
        //------------------------ ADD
        addAllUsers();
        addAllRestaurants();
        addAllFoods();
        addRoutes();

        //REGISTER
        registerUser.add(voltarLogar);
        registerUser.add(registerUserButton);

        //LOGIN
        login.add(logar);
        login.add(registerU);
        login.add(registerR);
        login.add(registerF);

        //SHOPPING
        shopping.add(deslogar);
        shopping.add(carrinho);

        //CART
        cart.add(voltar);
        cart.add(comprar);

        //QUIETUS
        quietus.add(ok);

        //REGISTER RESTAURANT
        registerRest.add(registerRestButton);
        registerRest.add(voltarResLogar);

        //REGISTER COMIDA
        registerFood.add(registerFoodButton);
        registerFood.add(voltarFoodLogar);

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
    public void cadastrarRestaurante(String[] allInOne, boolean showMessage){
        cadastrarRestaurante(allInOne[0], Integer.parseInt(allInOne[1]), Integer.parseInt(allInOne[2]), showMessage);
    }

    public boolean cadastrarRestaurante(String nome, int x, int y, boolean showMessage){
        for (Restaurant rest : restaurants){
            if (Objects.equals(nome, rest.getName()) || !isAlpha(nome)){
                System.out.println(nome);
                new ErrorPopUp("ATENTITON","It already has a restaurant with this name, or is invalid");
                return false;
            }
        }
        restaurants.add(new Restaurant(idRes, nome, x,y));
        idRes += 1;
        if (showMessage){
            new SuccessPopUp("Success", "Restaurant ["+nome+"] was created!\nNow add new foods!!!");
        }
        return true;
    }
    public boolean cadastrarUsers(String nome, String CPF, int x, int y, Role role, boolean showMessage){
        for (User user : users){
            if (Objects.equals(nome, user.getName()) || !isAlpha(nome)){
                System.out.println(nome);
                new ErrorPopUp("ATENTITON","It already has a User with this name, or is invalid");
                return false;
            }
        }
        if (registerUser.admLogin()){
            users.add(new User(idUser, nome, CPF, x,y, role));
        }else{
            users.add(new User(idUser, nome, CPF, x,y));
        }
        idUser += 1;
        if (showMessage){
            new SuccessPopUp("Success", "["+nome+"] you create your account");
        }
        return true;
    }
    public void cadastrarUsers(String nome, String CPF, int x, int y, boolean showMessage){
        cadastrarUsers(nome, CPF, x, y, Role.USER, showMessage);
    }


    private void addRoutes(){
        // ACTUALLY AN ACTION
        registerUserButton.addActionListener(e ->{
            if (cadastrarUsers(registerUser.getNome(), registerUser.getCpf(),Integer.parseInt(registerUser.getXL()),Integer.parseInt(registerUser.getYL()), registerUser.getRole(), true)){
                registerUser.clean();
                voltarLogar.doClick();
            }
        });
        registerRestButton.addActionListener(e ->{
            if (cadastrarRestaurante(registerRest.getNome(), registerRest.getX(), registerRest.getX(), true)){
                registerRest.clean();
                registerF.doClick();
                registerFood.getLastRestaurant();
            }
        });
        registerFoodButton.addActionListener(e ->{
            if (registerFood.getRestaurant().adicionarLanche(registerFood.getFoodName(), registerFood.getFoodPrice(), true)){
                registerFood.clean();
            }
        });
        // LOGIN -> REGISTER
        registerU.addActionListener(e -> {
            login.logar();
            login.setVisible(false);

            registerUser.setUser(login.getLoggedUser());
            registerUser.setVisible(true);
            setContentPane(registerUser);
        });
        // LOGIN -> REGISTER RESTAURANT
        registerR.addActionListener(e -> {
            if(!login.logar()){
                return;
            }
            if (login.getLoggedUser().getRole() != Role.ADM){
                return;
            }
            login.setVisible(false);

            registerRest.setVisible(true);
            setContentPane(registerRest);
        });
        // LOGIN -> REGISTER COMIDA
        registerF.addActionListener(e -> {
            if(!login.logar()){
                return;
            }
            if (login.getLoggedUser().getRole() != Role.ADM){
                return;
            }
            login.setVisible(false);
            registerRest.setVisible(false);

            registerFood.setRestaurants(getRestaurants());
            registerFood.refresh();
            registerFood.setVisible(true);
            setContentPane(registerFood);
        });
        // REGISTER RESTAURANT -> LOGIN
        voltarResLogar.addActionListener(e->{
            registerRest.setVisible(false);

            login.setVisible(true);
            setContentPane(login);
        });
        // REGISTER -> COMIDA LOGIN
        voltarFoodLogar.addActionListener(e->{
            registerFood.setVisible(false);

            login.setVisible(true);
            setContentPane(login);
        });
        // REGISTER -> LOGIN
        voltarLogar.addActionListener(e -> {
            registerUser.setVisible(false);

            login.setVisible(true);
            setContentPane(login);
        });
        // LOGIN -> SHOPPING
        logar.addActionListener(e -> {
            if(!login.logar()){
                return;
            }
            login.setVisible(false);

//            cart.setUser(login.getLoggedUser());
//            cart.setAllRequests(getPedidos());
//            cart.setAllRestaurants(getRestaurants());
            System.out.println("zzzzz");
            System.out.println(getPedidos());
            System.out.println("zzzzz");
            shopping.setAllRequests(getPedidos());
            shopping.cleanFood();
            shopping.setAllRestaurants(getRestaurants());
            shopping.setUser(login.getLoggedUser());
            shopping.refresh();
            shopping.setVisible(true);
            setContentPane(shopping);
        });
        // SHOPPING -> LOGIN
        deslogar.addActionListener(e ->{
            shopping.setVisible(false);

            login.setVisible(true);
            setContentPane(login);
        });
        // SHOPPING -> CART
        carrinho.addActionListener(e ->{
            shopping.setVisible(false);
            shopping.setDefaultPrices();

            cart.setUser(login.getLoggedUser());
            cart.setAllRestaurants(getRestaurants());
            cart.setAllRequests(getPedidos());
            cart.updateRequests();
            cart.updatePrice();
            cart.setVisible(true);
            setContentPane(cart);
        });
        // CART -> QUIETUS
        comprar.addActionListener(e -> {
            if (cart.getModelRequests().isEmpty()){
                return;
            }
            cart.setVisible(false);

            quietus.setModelQuietus(cart.getModelRequests());
            quietus.refresh();
            quietus.setVisible(true);
            setContentPane(quietus);
        });
        // CART -> SHOPPING
        voltar.addActionListener(e ->{
            cart.setVisible(false);

            shopping.cleanFood();
            shopping.updateRequests();
            shopping.updatePrice();
            shopping.setVisible(true);
            setContentPane(shopping);
        });
        // QUIETUS -> CART
        ok.addActionListener(e->{
            quietus.setVisible(false);

            cart.finishShopping();
            cart.setVisible(true);
            setContentPane(cart);
        });

    }
    private void addAllUsers(){
        users.add(new User(idUser, "", "", 10,15, Role.ADM));
        idUser++;
        cadastrarUsers("Gustavo", "83", 50,50, false);
    }
    private void addAllRestaurants(){
        cadastrarRestaurante(cadRes.FirelinkShrimp(), false);
        cadastrarRestaurante(cadRes.SolaireSoup(), false);
        cadastrarRestaurante(cadRes.JohnGourmet(), false);
        cadastrarRestaurante(cadRes.Dadora(), false);
    }
    private void addAllFoods(){
        cadFoods.firelinkShrimp(restaurants.get(0));
        cadFoods.solaireSoup(restaurants.get(1));
        cadFoods.johnGourmet(restaurants.get(2));
        cadFoods.dadora(restaurants.get(3));
    }
}
