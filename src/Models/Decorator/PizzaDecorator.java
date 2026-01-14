package Models.Decorator;

import Models.Pizza;
import Models.Product;
 // PATTERN: DECORATOR
 // Through this decorator we can implement other additions to the pizza
 // An example is the ExtraIngredient class. Thanks to the structural decorator pattern,
 // we can add as many extra ingredients as we wish to.

 // By keeping a wrapped product here, we can use the children classes such as ExtraIngredient to
 // keep the wrapped product with the new ingredient into a new wrapped product and wrap again to add new ingredients
 // This reduces code complexity, and to access the full decorator objects we can use a loop until no more are found.

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