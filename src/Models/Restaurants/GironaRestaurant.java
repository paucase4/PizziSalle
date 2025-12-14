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

public class GironaRestaurant extends Restaurant {
    public GironaRestaurant() {
        super(Location.GIRONA);
    }

    @Override
    public void initializeLocalRecipes() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(Ingredient.BEEF);
        ingredients.add(Ingredient.HAM);
        ingredients.add(Ingredient.MUSHROOMS);
        ingredients.add(Ingredient.CHICKEN);
        ingredients.add(Ingredient.OLIVES);
        this.localRecipes.put("Girona", new Pizza("Girona", ingredients));
    }
}
