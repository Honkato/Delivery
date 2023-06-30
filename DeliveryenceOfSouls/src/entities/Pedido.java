package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Pedido {
    private final Restaurant restaurantPedido;
    private final User usuarioPedido;
    private final ArrayList<String> foodsCart = new ArrayList<>();

    public Restaurant getRestaurantPedido() {
        return restaurantPedido;
    }

    public User getUsuarioPedido() {
        return usuarioPedido;
    }

    public int getId() {
        return id;
    }

    private final int id;
    private boolean finalizado;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Pedido(int id,Restaurant restaurantPedido, User usuarioPedido) {
        this.id = id;
        this.restaurantPedido = restaurantPedido;
        this.usuarioPedido = usuarioPedido;
        this.x = usuarioPedido.getEndereco()[0];
        this.y = usuarioPedido.getEndereco()[1];
    }
    public boolean isEmpty(){
        return foodsCart.isEmpty();
    }

    public ArrayList<String> getFoodsCart() {
        return foodsCart;
    }

    public void addPedido(Food food){
        // NAO TA IMPRIMINDO CERTO ARRUMAR

        int index = 0;
        int qtd = 1;
        for (String f : foodsCart){
                String[] updateRequest = f.split(",");
                System.out.println("F: "+f);
                System.out.println("Food: "+food.getNome());
            if (Objects.equals(updateRequest[0], food.getNome())){
                foodsCart.remove(f);
                qtd += Integer.parseInt(updateRequest[1]);
                break;
            }
            index++;
        }
        foodsCart.add(index,food.getNome()+","+qtd+","+food.getPreco());
        System.out.println(Arrays.toString(foodsCart.toArray()));
        System.out.println(this);
    }
    public boolean isFinished(){
        return finalizado;
    }
    public void finish(){
        finalizado = true;
    }
//    public void removePedido(Food food){
//        // NAO TA IMPRIMINDO CERTO ARRUMAR
//
//        int index = 0;
//        int qtd = 1;
//        int newQtd = 0;
//        for (String f : foodsCart){
//                String[] updateRequest = f.split(",");
//                System.out.println("F: "+f);
//                System.out.println("Food: "+food.getNome());
//            if (Objects.equals(updateRequest[0], food.getNome())){
//                foodsCart.remove(f);
//                newQtd = Integer.parseInt(updateRequest[1])- qtd;
//                break;
//            }
//            index++;
//        }
//        if (newQtd <=0){
//            return;
//        }
//        foodsCart.add(index,food.getNome()+","+newQtd+","+food.getPreco());
//        System.out.println(Arrays.toString(foodsCart.toArray()));
//        System.out.println(this);
//    }
    public void removePedido(Food food, boolean keepWish){
        // NAO TA IMPRIMINDO CERTO ARRUMAR

        int index = 0;
        int qtd = 1;
        int newQtd = 0;
        for (String f : foodsCart){
                String[] updateRequest = f.split(",");
                System.out.println("F: "+f);
                System.out.println("Food: "+food.getNome());
            if (Objects.equals(updateRequest[0], food.getNome())){
                foodsCart.remove(f);
                newQtd = Integer.parseInt(updateRequest[1])- qtd;
                break;
            }
            index++;
        }
        if (newQtd <=0){
            if (keepWish){
                newQtd = 0;
            }else{
                return;
            }
        }
        foodsCart.add(index,food.getNome()+","+newQtd+","+food.getPreco());
        System.out.println(Arrays.toString(foodsCart.toArray()));
        System.out.println(this);
    }
//    public void removePedido(Food food){
//        int index = 0;
//        int qtd = 1;
//        for (String f : foodsCart){
//            String[] updateRequest = f.split(",");
//            System.out.println("F: "+f);
//            System.out.println("Food: "+food.getNome());
//            if (Objects.equals(f, food.getNome())){
//                qtd -= Integer.parseInt(updateRequest[1]);
//                break;
//            }
//            index++;
//        }
//        foodsCart.add(index,food+","+qtd);
//        if (qtd <= 0){
//            foodsCart.remove(index);
//        }
//    }


    @Override
    public String toString() {

        return "Pedido{" +
                "id=" + id +
                ", restaurantPedido=" + restaurantPedido.getName() +
                ", usuarioPedido=" + usuarioPedido.getName() +
                ", foods=" + Arrays.toString(foodsCart.toArray()) +
                ", finalizado=" + finalizado +
//                ", quantity=" + Arrays.toString(foodsCart.toArray()) +
                '}';
    }

    public float precoTotal(){
        float preco = 0;
        for (String f : foodsCart) {
            String[] food = f.split(",");
            preco += Integer.parseInt(food[1])*Float.parseFloat(food[2]);
        }
        return preco;
    }

}
