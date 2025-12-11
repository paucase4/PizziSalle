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

public class LleidaRestaurant extends Restaurant {
    public LleidaRestaurant() {
        super(Location.LLEIDA);
    }
    @Override
    public void initializeLocalRecipes() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(Ingredient.ARTICHOKE);
        this.localRecipes.put("Lleida", new Pizza("Lleida", ingredients));
    }
}
