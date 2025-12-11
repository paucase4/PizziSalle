package Models;

import Enums.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PizzaDecorator implements Product {
    protected Product wrappedProduct;

    public PizzaDecorator(Product product) {
        this.wrappedProduct = product;
    }

    @Override
    public String getName() {
        return wrappedProduct.getName();
    }

    @Override
    public double getPrice() {
        return wrappedProduct.getPrice();
    }

    public Product getWrappedProduct() {
        return wrappedProduct;
    }
    public Pizza getBasePizza() {
        Product current = wrappedProduct;
        while (current instanceof PizzaDecorator) {
            current = ((PizzaDecorator) current).wrappedProduct;
        }
        return (current instanceof Pizza) ? (Pizza) current : null;
    }

}