package Models.Restaurants;

import Enums.Beverage;
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
            RecipesLoader.loadCommonRecipes("src/Models/ingredients.txt");

    protected Map<String, Pizza> localRecipes = new HashMap<>();
    Location loc;

    protected abstract void initializeLocalRecipes();
    public Restaurant(Location loc){
        this.loc = loc;
    }
    public List<Product> getAvailableProducts() {
        Map<String, Product> all = new HashMap<>(COMMON_RECIPES);
        all.putAll(localRecipes);
        all.put("Water", Beverage.WATER);
        all.put("Beer", Beverage.BEER);
        all.put("Soda", Beverage.COLA);
        return new ArrayList<>(all.values());
    }

  //  public abstract Pizza createPizza (String name);
}
