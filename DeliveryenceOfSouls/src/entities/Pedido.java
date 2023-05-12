package entities;

public class Pedido {
    private final Restaurant restaurantPedido;
    private final User usuarioPedido;
    private final int id;

    public Pedido(int id,Restaurant restaurantPedido, User usuarioPedido) {
        this.id = id;
        this.restaurantPedido = restaurantPedido;
        this.usuarioPedido = usuarioPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "restaurantPedido=" + restaurantPedido +
                ", usuarioPedido=" + usuarioPedido +
                ", id=" + id +
                '}';
    }

    String imprimirPedido(){
        return toString();
    }

}
