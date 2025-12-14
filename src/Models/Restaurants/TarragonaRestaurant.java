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
         }
    @Override
    public void initializeLocalRecipes() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(Ingredient.TUNA);
        ingredients.add(Ingredient.PRAWNS);
        ingredients.add(Ingredient.ONION);
        ingredients.add(Ingredient.HAM);
        ingredients.add(Ingredient.OLIVES);
        this.localRecipes.put("tarragona", new Pizza("Tarragona", ingredients));
    }
}
