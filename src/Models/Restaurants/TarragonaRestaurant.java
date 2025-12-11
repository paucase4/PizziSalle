package Models.Restaurants;

import Enums.Beverage;
import Enums.Ingredient;
import Enums.Location;
import Models.Pizza;
import Models.Product;
import Models.Restaurants.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TarragonaRestaurant extends Restaurant {
    public TarragonaRestaurant() {
        super(Location.TARRAGONA);
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(Ingredient.ARTICHOKE);
        this.localRecipes.put("tarragona", new Pizza("tarragona", ingredients));
    }
    @Override
    public List<Product> getAvailableProducts() {
        Map<String, Product> all = new HashMap<>(COMMON_RECIPES);
        all.putAll(localRecipes);
        all.put("Water", Beverage.WATER);
        all.put("Beer", Beverage.BEER);
        all.put("Soda", Beverage.COLA);
        return new ArrayList<>(all.values());
    }
    @Override
    public Pizza createPizza(String name) {
        return null;
    }
}
