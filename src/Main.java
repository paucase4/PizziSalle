import Enums.Beverage;
import Enums.Crust;
import Enums.Ingredient;
import Enums.Location;
import Models.*;
import Models.Decorator.ExtraIngredient;
import Models.Decorator.PizzaDecorator;
import Models.Restaurants.*;
import Models.Singleton.OrderManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        takeOrder();
    }
    public static Restaurant getRestaurant(Location loc) {
        switch (loc) {
            case BARCELONA:
                return new BarcelonaRestaurant();
            case GIRONA:
                return new GironaRestaurant();
            case LLEIDA:
                return new LleidaRestaurant();
            case TARRAGONA:
                return new TarragonaRestaurant();
            default:
                throw new IllegalArgumentException("Unknown location: " + loc);
        }
    }
    public static void takeOrder() {
        System.out.println("Before taking the order, please give us your details:");
        Customer c = createCustomerFromInput();
        Order order = new Order();
        OrderManager orderManager = OrderManager.getInstance();

        order.setLocation(getRandomLocation());
        order.setCustomer(c);

        Restaurant rest = getRestaurant(order.getLocation());

        int itemCount = 0;

        ArrayList<Product> orderedProducts = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();

        while (itemCount < 10) {
            System.out.print("\nWhat product do you want? Enter number (if your order is complete type -1): ");

            Product p = orderManager.printAndSelectProducts(rest, c);
            if (p == null) {
                break;
            }

            orderedProducts.add(p);
            quantities.add(1);
            itemCount++;
        }

        System.out.println("\n\n===== FINAL ORDER =====\n");
        System.out.println("Customer: " + c.getName());
        System.out.println("Phone: " + c.getPhone());
        System.out.println("Address: " + c.getAddress());
        System.out.println("\nProducts ordered:\n");


        for (int i = 0; i < orderedProducts.size(); i++) {
            Product product = orderedProducts.get(i);
            int quantity = quantities.get(i);

            System.out.println((i + 1) + ". " + product.getName() + " x" + quantity);

            if (product instanceof PizzaDecorator) {
                Pizza basePizza = ((PizzaDecorator) product).getBasePizza();
                if (basePizza != null) {
                    System.out.println("   Base ingredients: " + basePizza.getIngredients());
                }

                List<String> extras = ((ExtraIngredient)product).getAllExtraIngredients();
                if (!extras.isEmpty()) {
                    System.out.println("   Extra ingredients: " + String.join(", ", extras));
                }
            }
            // Check if it's a regular pizza (no extras)
            else if (product instanceof Pizza) {
                System.out.println("   Ingredients: " + ((Pizza) product).getIngredients());
            }
            // It's a beverage or other product
            else {
                System.out.println("   " + product.getName());
            }
            System.out.println();

        }

        System.out.println("Total items: " + itemCount);
    }

    public static Customer createCustomerFromInput() {
        Scanner sc = new Scanner(System.in);
        Customer c = new Customer();

        System.out.print("Enter the customer's name: ");
        c.setName(sc.nextLine());

        System.out.print("Enter the phone number: ");
        c.setPhone(sc.nextLine());

        int age = -1;
        while (age < 1 || age > 120) {
            System.out.print("Enter your age: ");
            if (sc.hasNextInt()) {
                age = sc.nextInt();
                sc.nextLine(); // Consume the leftover newline
                if (age < 1 || age > 120) {
                    System.out.println("Invalid, please try again.");
                } else {
                    c.setAdult(age >= 18);
                }
            } else {
                System.out.println("Invalid, please enter a number.");
                sc.next(); // Clear invalid input
            }
        }

        System.out.print("Enter the address: ");
        c.setAddress(sc.nextLine());

        System.out.print("Is this their first time buying? (y/n): ");
        String response = sc.nextLine().toLowerCase();
        c.setFirst(response.startsWith("y"));


        System.out.println("Customer created successfully.");
        return c;
    }

    public static Location getRandomLocation() {
        return Location.values()[new Random().nextInt(Location.values().length)];
    }

}



