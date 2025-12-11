package Models;

import Enums.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtraIngredient extends PizzaDecorator {
    private Ingredient ingredient;
    private static final double EXTRA_INGREDIENT_PRICE = 1.50;

    public ExtraIngredient(Product product, Ingredient ingredient) {
        super(product);
        this.ingredient = ingredient;
    }

    @Override
    public double getPrice() {
        return 0;
    }
    // super.getPrice() + EXTRA_INGREDIENT_PRICE;
    public String getIngredient() {
        return ingredient.toString();
    }

    public List<String> getAllExtraIngredients() {
        List<String> extras = new ArrayList<>();
        Product current = this;

        while (current instanceof ExtraIngredient) {
            extras.add(((ExtraIngredient) current).getIngredient());
            current = ((ExtraIngredient) current).getWrappedProduct();
        }

        // Reverse to show ingredients in the order they were added
        Collections.reverse(extras);
        return extras;
    }
}
