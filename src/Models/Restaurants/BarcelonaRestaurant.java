package Models.Restaurants;

import Enums.Beverage;
import Enums.Ingredient;
import Enums.Location;
import Models.Pizza;
import Models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarcelonaRestaurant extends Restaurant {

    public BarcelonaRestaurant() {
        super(Location.BARCELONA);
    }

    @Override
    public void initializeLocalRecipes() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(Ingredient.BRIE);
        ingredients.add(Ingredient.ONION);
        ingredients.add(Ingredient.HAM);
        ingredients.add(Ingredient.OLIVES);
        this.localRecipes.put("barcelona", new Pizza("Barcelona", ingredients));
    }
 /*       @Override
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
    }*/

}
