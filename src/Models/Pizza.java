package Models;

import Enums.Ingredient;

import java.util.ArrayList;
import java.util.List;


public class Pizza implements Product {
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    private String name;
    private double price;

    public Pizza(String name, ArrayList<Ingredient> ingredientsSpecial) { // , double price
        this.name = name;
       // this.price = price;
        this.ingredients.add(Ingredient.TOMATO_SAUCE);
        this.ingredients.add(Ingredient.CHEESE);
        this.ingredients.addAll(ingredientsSpecial);
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
