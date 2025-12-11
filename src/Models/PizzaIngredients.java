package Models;

import Enums.Ingredient;
import java.util.*;

public class PizzaIngredients {

    private static final Map<String, List<Ingredient>> recipes = new HashMap<>();

    static {

        recipes.put("margherita", List.of(
        ));

        // 2 INGREDIENTS
        recipes.put("hawaiian", List.of(
                Ingredient.HAM,
                Ingredient.PINEAPPLE
        ));

        // 3 INGREDIENTS
        recipes.put("bacon crispy", List.of(
                Ingredient.HAM,
                Ingredient.CHICKEN,
                Ingredient.BACON
        ));

        recipes.put("american", List.of(
                Ingredient.FRANKFURT,
                Ingredient.BACON,
                Ingredient.EGG
        ));

        recipes.put("traviata", List.of(
                Ingredient.BACON,
                Ingredient.SAUSAGE,
                Ingredient.ONION
        ));

        // 4 INGREDIENTS
        recipes.put("burger", List.of(
                Ingredient.MINIBURGERS,
                Ingredient.EGG,
                Ingredient.BACON,
                Ingredient.ONION
        ));

        recipes.put("castellera", List.of(
                Ingredient.ONION,
                Ingredient.TUNA,
                Ingredient.PEPPERONI,
                Ingredient.OLIVES
        ));

        recipes.put("cowboy", List.of(
                Ingredient.BBQ_SAUCE,
                Ingredient.CHEDDAR,
                Ingredient.ROQUEFORT,
                Ingredient.BACON
        ));

        recipes.put("texas", List.of(
                Ingredient.BBQ_SAUCE,
                Ingredient.ONION,
                Ingredient.TOMATO_SLICES,
                Ingredient.CHICKEN
        ));

        recipes.put("coast", List.of(
                Ingredient.TUNA,
                Ingredient.ANCHOVIES,
                Ingredient.PRAWNS,
                Ingredient.PINEAPPLE
        ));

        recipes.put("bbq", List.of(
                Ingredient.BBQ_SAUCE,
                Ingredient.BEEF,
                Ingredient.BACON,
                Ingredient.CHICKEN
        ));

        recipes.put("diablo", List.of(
                Ingredient.HAM,
                Ingredient.BEEF,
                Ingredient.BACON,
                Ingredient.CHICKEN
        ));

        recipes.put("carbonara", List.of(
                Ingredient.CARBONARA_SAUCE,
                Ingredient.BACON,
                Ingredient.ONION,
                Ingredient.MUSHROOMS
        ));

        recipes.put("spanish", List.of(
                Ingredient.SERRANO,
                Ingredient.BRIE,
                Ingredient.OLIVES,
                Ingredient.MUSHROOMS
        ));

        recipes.put("4 cheeses", List.of(
                Ingredient.MOZZARELLA,
                Ingredient.EMMENTAL,
                Ingredient.ROQUEFORT,
                Ingredient.CHEDDAR
        ));

        recipes.put("pepperoni", List.of(
                Ingredient.PEPPERONI,
                Ingredient.HAM,
                Ingredient.BEEF,
                Ingredient.BACON
        ));

        // 5 INGREDIENTS
        recipes.put("vegetal", List.of(
                Ingredient.ONION,
                Ingredient.BELL_PEPPER,
                Ingredient.TOMATO_SLICES,
                Ingredient.ARTICHOKE,
                Ingredient.MUSHROOMS
        ));

        recipes.put("barcelona", List.of(
                Ingredient.ONION,
                Ingredient.BEEF,
                Ingredient.BRIE,
                Ingredient.HAM,
                Ingredient.OLIVES
        ));

        recipes.put("girona", List.of(
                Ingredient.BEEF,
                Ingredient.HAM,
                Ingredient.MUSHROOMS,
                Ingredient.CHICKEN,
                Ingredient.OLIVES
        ));

        recipes.put("tarragona", List.of(
                Ingredient.TUNA,
                Ingredient.PRAWNS,
                Ingredient.ONION,
                Ingredient.HAM,
                Ingredient.OLIVES
        ));

        recipes.put("lleida", List.of(
                Ingredient.BBQ_SAUCE,
                Ingredient.BEEF,
                Ingredient.CHICKEN,
                Ingredient.MUSHROOMS,
                Ingredient.OLIVES
        ));

        // 6 INGREDIENTS
        recipes.put("6 cheeses", List.of(
                Ingredient.MOZZARELLA,
                Ingredient.GOAT_CHEESE,
                Ingredient.BRIE,
                Ingredient.EMMENTAL,
                Ingredient.ROQUEFORT,
                Ingredient.CHEDDAR
        ));

        recipes.put("mallorca", List.of(
                Ingredient.GOAT_CHEESE,
                Ingredient.EMMENTAL,
                Ingredient.CHEDDAR,
                Ingredient.BRIE,
                Ingredient.SOBRASSADA,
                Ingredient.OLIVES
        ));

        recipes.put("carbonara deluxe", List.of(
                Ingredient.CARBONARA_SAUCE,
                Ingredient.BACON,
                Ingredient.ONION,
                Ingredient.MUSHROOMS,
                Ingredient.GOAT_CHEESE,
                Ingredient.HONEY
        ));
    }

    public static List<Ingredient> getIngredients(String name) {
        return recipes.get(name.toLowerCase());
    }
}
