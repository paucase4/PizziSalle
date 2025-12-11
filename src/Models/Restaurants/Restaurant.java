package Models.Restaurants;

import Enums.Location;
import Models.Pizza;
import Models.Product;
import Models.RecipesLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Restaurant {
    protected static final Map<String, Pizza> COMMON_RECIPES =
            RecipesLoader.loadCommonRecipes("C:\\Users\\pauca\\OneDrive\\Escritorio\\test cesc\\untitled\\src\\Models\\ingredients.txt");

    protected Map<String, Pizza> localRecipes = new HashMap<>();
    Location loc;
    // ArrayList<Product> products = new ArrayList<>();

    public Restaurant(Location loc){
        this.loc = loc;
        switch (loc) {
            // case GIRONA -> ;
        }
    }
    public abstract List<Product> getAvailableProducts();
    public abstract Pizza createPizza (String name);
}
