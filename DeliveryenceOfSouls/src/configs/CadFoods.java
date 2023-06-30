package configs;

import entities.Restaurant;

public class CadFoods {
    public CadFoods(){
    }
    public void firelinkShrimp(Restaurant restaurant){
        restaurant.adicionarLanche("Shrimp Burger", 5.99F);
        restaurant.adicionarLanche("Ashes of Lords of Cinders", 29.99F);
        restaurant.adicionarLanche("Mainden's Eyeball Cereal", 15.99F);
    }
    public void solaireSoup(Restaurant restaurant){
        restaurant.adicionarLanche("Estus Soup", 20.99F);
        restaurant.adicionarLanche("Solar Ray", 23.99F);
        restaurant.adicionarLanche("Praise The Sunday", 30.99F);
    }
    public void johnGourmet(Restaurant restaurant){
        restaurant.adicionarLanche("Umbilical Cord Noodles", 99.99F);
        restaurant.adicionarLanche("Blood Beast Soup", 69.99F);
        restaurant.adicionarLanche("Celestial Being's Part", 105.99F);
    }
    public void dadora(Restaurant restaurant){
        restaurant.adicionarLanche("Esfirra Musica Popular Brasileira", 11.99F);
        restaurant.adicionarLanche("O melhor brownie do mundo", 32.99F);
        restaurant.adicionarLanche("Marmita da Semana", 19.99F);
    }
}
