package entities;

import java.util.ArrayList;

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
    public void adicionarLanche(String nome, float preco){
        comidas.add(new Food(idLancheAtual, nome, preco));
        idLancheAtual += 1;
    }
    public void removerComida(int id){
        comidas.removeIf(comida -> comida.id == id);
    }


}
