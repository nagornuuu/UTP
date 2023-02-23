/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad2;


public class Purchase {

    String idOfClient, name, product;
    double quantity, price;

    public Purchase(String idOfClient, String name, String product, double quantity, double price) {
        this.idOfClient = idOfClient;
        this.name = name;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public String toString() {
        return idOfClient + ";" +
                name + ";" +
                product + ";" +
                quantity + ";" +
                price;
    }
}
