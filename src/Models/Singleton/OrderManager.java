package Models.Singleton;

import Enums.Beverage;
import Enums.Crust;
import Enums.Ingredient;
import Models.Customer;
import Models.Decorator.ExtraIngredient;
import Models.Pizza;
import Models.Product;
import Models.Restaurants.Restaurant;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderManager {
    // PATTERN: Singleton
    // This class ensures that only one instance of OrderManager exists
    // throughout the entire application. The constructor is private
    // to prevent direct instantiation, and access to the single instance
    // is provided through the static getInstance() method.
    //
    // The instance is created: it is only initialized the first
    // time getInstance() is called. This guarantees a single shared
    // OrderManager that coordinates order-related operations (such as
    // product selection and customization) from a centralized point.
    //
    // By using the Singleton pattern, the application avoids inconsistent
    // state and duplicated logic that could arise from having multiple
    // order managers, ensuring controlled access and global consistency
    // in order handling.

    private static OrderManager instance;

    private OrderManager() {}

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public Product printAndSelectProducts(Restaurant rest, Customer c) {
        ArrayList<Product> products = (ArrayList<Product>) rest.getAvailableProducts();
        products.sort((p1, p2) -> {
            boolean p1IsBeverage = p1 instanceof Beverage;
            boolean p2IsBeverage = p2 instanceof Beverage;

            if (p1IsBeverage && !p2IsBeverage) return 1;
            if (!p1IsBeverage && p2IsBeverage) return -1;
            return 0;
        });
        boolean bevs = false;
        System.out.println("\n\n===== Welcome to PIZZISALLE "+ rest.getLocationRestaurant()+"=====\n");
        System.out.println("===== PIZZAS =====\n");
        int i = 1;
        for (Product p: products) {
            if (p instanceof Beverage) {
                if (!bevs) {
                    System.out.println("\n===== BEVERAGES =====\n");
                    bevs = true;
                }
            }
            if (!(p.getName().toLowerCase().contains("beer") && !c.isAdult())) {
                System.out.println(i + ". " + p.getName());
            } else i--;
            if (p instanceof Pizza) {
                System.out.println(" (Ingredients: " + ((Pizza)p).getIngredients() + ")");
            }
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice < 1 || choice > products.size()) {
            System.out.print("\nChoose product number (or -1 to finish order): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == -1) {
                    return null;
                }
                if (choice < 1 || choice > products.size()) {
                    System.out.println("Invalid, please try again.");
                }

            } else {
                System.out.println("Invalid, please enter a number.");
                scanner.next();
            }
        }

        Product selectedProduct = products.get(choice - 1);
        System.out.println("You selected: " + selectedProduct.getName() + "\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Extra ingredients and Crust
        if (selectedProduct instanceof Pizza) {
            Pizza orderedPizza = new Pizza(selectedProduct.getName(), ((Pizza)selectedProduct).getIngredients());
            orderedPizza.setCrustType(chooseCrust());
            return addExtraIngredients(orderedPizza, scanner);
        }
        return selectedProduct;

    }

    private static Crust chooseCrust()  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect crust type:");
        System.out.println("1. Original");
        System.out.println("2. Thin");
        System.out.println("3. Sicilian");

        System.out.print("Enter your choice (1-3): ");

        int crustChoice = scanner.nextInt();
        scanner.nextLine();

        switch (crustChoice) {
            case 1:
                return Crust.ORIGINAL;
            case 2:
                return Crust.THIN;
            case 3:
                return Crust.SICILIAN;
            default:
                System.out.println("Invalid choice. Defaulting to Original.");
                return Crust.ORIGINAL;
        }

    }
    private static Product addExtraIngredients(Product pizza, Scanner scanner) {


        System.out.print("\nDo you want to add extra ingredients? (yes/no): ");
        String response = scanner.next().toLowerCase();

        if (!response.equals("yes") && !response.equals("y")) {
            return pizza;
        }
        System.out.println("\nAvailable extra ingredients:");
        Ingredient[] availableIngredients = Ingredient.values();

        for (int i = 0; i < availableIngredients.length; i++) {
            System.out.println((i + 1) + ". " + availableIngredients[i]);
        }

        Product decoratedPizza = pizza;
        int extrasAdded = 0;

        while (((Pizza)pizza).getIngredients().size() + extrasAdded < 10) {
            System.out.print("\nChoose ingredient number (or 0 to finish): ");
            if (scanner.hasNextInt()) {
                int ingredientChoice = scanner.nextInt();

                if (ingredientChoice == 0) {
                    break;
                }

                if (ingredientChoice >= 1 && ingredientChoice <= availableIngredients.length) {
                    decoratedPizza = new ExtraIngredient(decoratedPizza,availableIngredients[ingredientChoice-1]);
                    extrasAdded++;
                    System.out.println("Added: " + availableIngredients[ingredientChoice - 1]);
                    int count = ((Pizza)pizza).getIngredients().size()+extrasAdded;
                    System.out.println("Number of ingredients in pizza: " + count + "/10");
                } else {
                    System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid input.");
                scanner.next();
            }
        }

        if (extrasAdded == 10) {
            System.out.println("Maximum number of extra ingredients reached.");
        }
        if (extrasAdded > 0) {
            System.out.println("\nFinal Product: " + decoratedPizza.getName());
            Pizza p = ((ExtraIngredient)decoratedPizza).getBasePizza();
            ArrayList<Ingredient> ingredients = p.getIngredients();
            System.out.println("Base Ingredients: " + ingredients);
            System.out.println("Extras: " + ((ExtraIngredient)decoratedPizza).getAllExtraIngredients() );

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return decoratedPizza;
    }
}
