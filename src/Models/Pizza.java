package Models;

import Enums.Ingredient;

import java.util.ArrayList;

public class Pizza implements Product {
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    private String name;
    private double price;

    public Pizza(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }
}
