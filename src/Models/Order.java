package Models;

import Enums.Location;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order {
    Location location;
    Customer customer;
    ArrayList<Product> prods = new ArrayList<Product>();
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void addProduct(Product prod) {
        prods.add(prod);
    }
}
