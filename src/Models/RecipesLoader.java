package Models;

import Enums.Ingredient;
import Models.Pizza;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipesLoader {

    public static Map<String, Pizza> loadCommonRecipes(String path) {
        Map<String, Pizza> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                // Saltar línies buides o comentaris
                if (line.isEmpty() || line.startsWith("#"))
                    continue;

                // Format: Name: ING1, ING2, ING3
                String[] parts = line.split(":");
                String name = parts[0].trim();

                List<Ingredient> ingredients = new ArrayList<>();

                if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                    String[] ingArr = parts[1].split(",");

                    for (String ing : ingArr) {
                        ingredients.add(
                                Ingredient.valueOf(ing.trim())  // converteix a enum
                        );
                    }
                }

                map.put(name, new Pizza(name, (ArrayList<Ingredient>) ingredients));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading recipes file", e);
        }

        return map;
    }
}
