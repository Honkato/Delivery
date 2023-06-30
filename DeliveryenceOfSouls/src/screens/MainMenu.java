package screens;

import components.Button;
import entities.Food;
import entities.Pedido;
import entities.Restaurant;
import entities.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenu extends JPanel  {
    private final JScrollPane scrollPaneRests = new JScrollPane();
    private final JScrollPane scrollPaneFoods = new JScrollPane();
    private final JScrollPane scrollPaneRequests = new JScrollPane();
    private final DefaultListModel<String> modelRests = new DefaultListModel<>();
    private final DefaultListModel<String> modelFoods = new DefaultListModel<>();
    private final DefaultListModel<String> modelRequests = new DefaultListModel<>();
    private final JList<String> listaRests = new JList<>(modelRests);
    private final JList<String> listaFoods = new JList<>(modelFoods);
    private final JList<String> listaRequests = new JList<>(modelRequests);
    private final Button addRequestButton = new Button("/\\",410,612,75,75);
    private final Button removeRequestButton = new Button("\\/",410,687,75,75);
    private final ArrayList<Restaurant> allRestaurants;
    private ArrayList<Pedido> allRequests;
    private Restaurant selectedRestaurant;
    private Food selectedFood;
    private User user;
    private JLabel userName = new JLabel();
    private JLabel selectedFoodPrice = new JLabel();
    private JLabel totalFoodPrice = new JLabel();
    private Button addToCart = new Button("add to cart", 385,500,100,50);
    private Button refresh = new Button("refresh", 0, 500, 100, 50);

    private int idPed = 0;
    private DecimalFormat df = new DecimalFormat("#.##");

    public ArrayList<Pedido> getAllRequests() {
        return allRequests;
    }

    public void setAllRequests(ArrayList<Pedido> allRequests) {
        this.allRequests = allRequests;
    }
    public void setDefaultPrices(){
        selectedFoodPrice.setText("Select a food");
        totalFoodPrice.setText("<html>Total:<br>R$0,00</html>");
        selectedRestaurant = null;
        selectedFood = null;
        listaRests.clearSelection();
        listaFoods.clearSelection();
    }

    public void setUser(User user) {
        this.user = user;
        System.out.println(user.getName());
        userName.setText(Objects.equals(user.getName(), "") ?"admin":user.getName());
        updateRequests();
    }

    public MainMenu(ArrayList<Restaurant> restaurants, ArrayList<Pedido> pedidos){
        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.BLACK);

        TitledBorder borderRestaurants;
        TitledBorder borderFoods;
        TitledBorder borderRequests;

        borderRestaurants = BorderFactory.createTitledBorder(blackline,"Restaurants");
        borderRestaurants.setTitleJustification(TitledBorder.CENTER);

        borderFoods = BorderFactory.createTitledBorder(blackline,"Foods");
        borderFoods.setTitleJustification(TitledBorder.CENTER);

        borderRequests = BorderFactory.createTitledBorder(blackline,"Requests");
        borderRequests.setTitleJustification(TitledBorder.CENTER);
//        jComp8.setBorder(title);
//
//        title = BorderFactory.createTitledBorder(
//                blackline, "title");
//        jComp9.setBorder(title);
//
//        title = BorderFactory.createTitledBorder(
//                loweredetched, "title");
//        title.setTitleJustification(TitledBorder.RIGHT);
//        jComp10.setBorder(title);
//
//        title = BorderFactory.createTitledBorder(
//                loweredbevel, "title");
//        title.setTitlePosition(TitledBorder.ABOVE_TOP);
//        jComp11.setBorder(title);
//
//        title = BorderFactory.createTitledBorder(
//                empty, "title");
//        title.setTitlePosition(TitledBorder.BOTTOM);
//        jComp12.setBorder(title);
//


        listaRests.setLayoutOrientation(JList.VERTICAL);
        listaFoods.setLayoutOrientation(JList.VERTICAL);
        listaRequests.setLayoutOrientation(JList.VERTICAL);

        this.allRestaurants = restaurants;
        this.allRequests = pedidos;

        add(scrollPaneRests);
        add(scrollPaneFoods);
        add(scrollPaneRequests);

        userName.setLocation(200, 0);
        userName.setSize(300,50);
        userName.setForeground(Color.RED);
        userName.setFont(new Font("arial",Font.BOLD,30));

        selectedFoodPrice.setLocation(255,450);
        selectedFoodPrice.setSize(300,40);
        selectedFoodPrice.setText("Select a food");
        selectedFoodPrice.setForeground(Color.ORANGE);
        selectedFoodPrice.setFont(new Font("arial",Font.BOLD,30));

        totalFoodPrice.setLocation(200,500);
        totalFoodPrice.setSize(200,100);
        totalFoodPrice.setText("<html>Total:<br>R$0,00</html>");
        totalFoodPrice.setForeground(Color.ORANGE);
        totalFoodPrice.setFont(new Font("arial",Font.BOLD,30));

//        listaRests.setSize(230, 300);
//        listaRests.setLocation(0,150);
        scrollPaneRests.setSize(230, 300);
        scrollPaneRests.setLocation(0,150);
        scrollPaneRests.setBorder(borderRestaurants);

//        listaFoods.setSize(230, 300);
//        listaFoods.setLocation(255, 150);
        scrollPaneFoods.setSize(229, 300);
        scrollPaneFoods.setLocation(255, 150);
        scrollPaneFoods.setBorder(borderFoods);

//        listaRequests.setSize(410,149);
//        listaRequests.setLocation(0,612);
        scrollPaneRequests.setSize(400,149);
        scrollPaneRequests.setLocation(0,612);
        scrollPaneRequests.setBorder(borderRequests);

        add(addRequestButton);
        add(removeRequestButton);
        add(refresh);
        add(addToCart);
        add(totalFoodPrice);
        add(selectedFoodPrice);
        add(listaRests);
        add(listaFoods);
        add(listaRequests);
        add(userName);

        addAllActions();

        setBackground(Color.BLUE);
        setLayout(null);
        setVisible(true);
    }
    public void refresh(){
        selectedFood = null;
        for (Restaurant rest: allRestaurants) {
            System.out.println("0");
            System.out.println(modelRests.contains(rest));
            if (!modelRests.contains(rest.getName())){
                modelRests.addElement(rest.getName());
            }
        }
        scrollPaneRests.setViewportView(listaRests);
    }
    public void updatePrice(){
        float totalPrice = 0;
        for (Pedido p : allRequests){
            if (p.getUsuarioPedido() == user && !p.isFinished()){
                totalPrice += p.precoTotal();
            }
        }
        totalFoodPrice.setText("<html>Total:<br>R$"+df.format(totalPrice).replace(".",",")+"</html>");
        /*
        for (String s : foodsCart){
            String[] request = s.split(",");
            float requestPrice = Float.parseFloat(request[request.length-2]);
            int requestQuantity = Integer.parseInt(request[request.length-1]);
            totalPrice += requestPrice*requestQuantity;
        } */
    }
    public void addRequest(){

        if (selectedRestaurant == null){
            return;
        }
        if (selectedFood == null){
            return;
        }
        boolean newRequest = true;

            for (Pedido p : allRequests) {
                if (p.getUsuarioPedido() == user && !p.isFinished() && p.getRestaurantPedido() == selectedRestaurant) {
                    p.addPedido(selectedFood);
                    newRequest = false;
                }
            }

        if (newRequest){
            Pedido p = new Pedido(idPed, selectedRestaurant, user);
            p.addPedido(selectedFood);
            allRequests.add(p);
            idPed++;
        }
        updatePrice();
        updateRequests();


    }
    public void removeRequest(){
        if (selectedRestaurant == null){
            return;
        }
        if (selectedFood == null){
            return;
        }
        for(Pedido p : allRequests){
            if (p.getUsuarioPedido() == user && !p.isFinished() && p.getRestaurantPedido() == selectedRestaurant){
                p.removePedido(selectedFood, false);
//                if (p.isEmpty()){
//                    allRequests.remove(p);
//                }
            }
        }

        updatePrice();
        updateRequests();
    }


    public void updateRequests(){
        System.out.println("TESTE:       "+modelRequests);
        modelRequests.removeAllElements();
        for (Pedido p : allRequests) {
            if (p.getUsuarioPedido() == user && !p.isFinished()) {
                ArrayList<String> foods = p.getFoodsCart();
                for (String food : foods) {
                        modelRequests.addElement(p.getRestaurantPedido().getName() + " : " + food);
                    }
                }
//                modelRequests.addElement(p.toString());
            }
//        this.allRequests = this.pedidos;
        scrollPaneFoods.setViewportView(listaFoods);
        scrollPaneRequests.setViewportView(listaRequests);
        }


//    void updateRequests(){
//        System.out.println("TESTE:       "+modelRequests);
//        boolean newRequest = true;
//        for (Pedido p : allRequests) {
//            if (user == p.getUsuarioPedido()) {
//                ArrayList<String> foods = p.getFoodsCart();
//                for (String food : foods) {
//                    for (Object element : modelRequests.toArray()){
//                        if ((element.toString().contains(p.getRestaurantPedido().getName() + " : " + food.split(",")[0]))) {
//                            newRequest = false;
//                        }
//                    }
//                    if (newRequest){
//                        modelRequests.addElement(p.getRestaurantPedido().getName() + " : " + food);
//                    }
//                    System.out.println( modelRequests.get(0).contains(p.getRestaurantPedido().getName() + " : " + food.split(",")[0]));
//                }
////                modelRequests.addElement(p.toString());
//            }
//        }
//
//    }
    public void addAllActions(){
        refresh.addActionListener(e-> refresh());
        addToCart.addActionListener(e-> addRequest());
//        addRequestButton.addActionListener(e-> addRemoveRequest(););
//        removeRequestButton.addActionListener(e -> addRemoveRequest(););
        addRequestButton.addActionListener(e -> addRequest());
        removeRequestButton.addActionListener(e -> removeRequest());

        listaRequests.addListSelectionListener(e ->{
            if (!e.getValueIsAdjusting()){
                return;
            }
//            listaFoods.clearSelection();
//            listaRests.clearSelection();
//            selectedFood = null;
// /*           selectedFoodPrice.setText("Select a food");
            String request = listaRequests.getSelectedValue();

            System.out.println(request);
            String restaurante = request.split(" : ")[0];
            String comida = request.split(" : ")[1].split(",")[0];
//            System.out.println(restaurante);
//            System.out.println(comida);
            for (Restaurant rest : allRestaurants){
                if (Objects.equals(rest.getName(), restaurante)){
                    this.selectedRestaurant = rest;
                }
            }
            for (Food food: this.selectedRestaurant.getCardapio()){
                if (Objects.equals(food.getNome(), comida)){
                    selectedFood = food;
                    selectedFoodPrice.setText("R$"+ df.format(selectedFood.getPreco()).replace(".",","));
                }
            }
            System.out.println(selectedRestaurant);
            System.out.println(selectedFood);
//            /*

            System.out.println(selectedRestaurant.getName());
            System.out.println(selectedFood.getNome());
//             */
        });

        listaFoods.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()){
                return;
            }
            System.out.println(listaRequests.isSelectionEmpty());
//            listaRequests.clearSelection();
            selectedFood = null;
            selectedFoodPrice.setText("Select a food");
            for (Food food: selectedRestaurant.getCardapio()){
                if (Objects.equals(food.getNome(), listaFoods.getSelectedValue())){
                    selectedFood = food;
                    selectedFoodPrice.setText("R$"+ df.format(selectedFood.getPreco()).replace(".",","));
                }
            }
            System.out.println(selectedFood);
            System.out.println("TESTE:       "+modelRequests);
        });
        listaRests.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()){
                return;
            }
            selectedFood = null;
//            System.out.println(listaRests.getSelectedValue());
            modelFoods.removeAllElements();
            for (Restaurant rest : allRestaurants){
                if (Objects.equals(rest.getName(), listaRests.getSelectedValue())){
                    selectedRestaurant = rest;
                }
            }
            for (Food food : selectedRestaurant.getCardapio()){
                if (!modelFoods.contains(food.getNome())){
                    modelFoods.addElement(food.getNome());
                }
            }

        });

    }
}
