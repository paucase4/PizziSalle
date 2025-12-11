package Models.Restaurants;

import Enums.Beverage;
import Enums.Ingredient;
import Enums.Location;
import Models.Pizza;
import Models.PizzaIngredients;
import Models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarcelonaRestaurant extends Restaurant {

    public BarcelonaRestaurant() {
        super(Location.BARCELONA);
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(Ingredient.BRIE);
        this.localRecipes.put("barcelona", new Pizza("barcelona", ingredients));
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
    public Pizza createPizza(String type) {

        if (type.equalsIgnoreCase("local")) {
            return new Pizza(
                    "Barcelona Special",
                    (ArrayList<Ingredient>) PizzaIngredients.getIngredients("barcelona")
            );
        }

        // si no és pizza local → receptari global
        ArrayList<Ingredient> ingredients =  (ArrayList<Ingredient>) PizzaIngredients.getIngredients(type);

        if (ingredients == null)
            return null;

        return new Pizza(type, ingredients);
    }

}
