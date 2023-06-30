package entities;

import components.ErrorPopUp;

import java.util.ArrayList;
import java.util.Objects;

public class Restaurant {
    private String name;
    private int id;
    private int x;
    private int y;
    private int idLancheAtual = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int[] getLocation() {
        return new int[] {x,y};
    }
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private final ArrayList<Food> comidas = new ArrayList<>();

    public ArrayList<Food> getCardapio() {
        return comidas;
    }

    public Restaurant(int id, String name, int x, int y){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (c == ' '){
                continue;
            }
            if (c == '\''){
                continue;
            }
            if(!Character.isLetter(c)) {
                System.out.println(c);
                return false;
            }
        }

        return true;
    }
    public void adicionarLanche(String nome, float preco){
        for (Food food : getCardapio()){

            if (Objects.equals(nome, food.getNome()) || !isAlpha(nome)){
                System.out.println(nome);
                new ErrorPopUp("ATENTITON","It already has a Food with this name, or is invalid");
                return;
            }
        }
        comidas.add(new Food(idLancheAtual, nome, preco));
        idLancheAtual += 1;
    }
    public Food getFood(String food){
        for(Food f : comidas){
            if (Objects.equals(food, f.getNome())){
                return f;
            }
        }
        return null;
    }
    public void removerComida(int id){
        comidas.removeIf(comida -> comida.id == id);
    }


}
