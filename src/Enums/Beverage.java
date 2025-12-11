package Enums;

import Models.Product;

public enum Beverage implements Product {
    COLA(1.5),
    WATER(1.0),
    BEER(2.0);

    private final double price;

    Beverage(double price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public double getPrice() {
        return price;
    }
}

