package Models.Template;

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

public class PhoneOrderProcessor extends OrderProcessor {

    private Customer customer;
    private Order order;
    private Restaurant restaurant;

    private final ArrayList<Product> orderedProducts = new ArrayList<>();
    private final ArrayList<Integer> quantities = new ArrayList<>();

    @Override
    protected void createCustomer() {
        Scanner sc = new Scanner(System.in);
        customer = new Customer();

        System.out.print("Enter the customer's name: ");
        customer.setName(sc.nextLine());

        System.out.print("Enter the phone number: ");
        customer.setPhone(sc.nextLine());

        int age;
        do {
            System.out.print("Enter your age: ");
            while (!sc.hasNextInt()) sc.next();
            age = sc.nextInt();
            sc.nextLine();
        } while (age < 1 || age > 120);

        customer.setAdult(age >= 18);

        System.out.print("Enter the address: ");
        customer.setAddress(sc.nextLine());

        System.out.print("Is this their first time buying? (y/n): ");
        customer.setFirst(sc.nextLine().toLowerCase().startsWith("y"));
    }

    @Override
    protected void createOrder() {
        order = new Order();
        order.setCustomer(customer);
        order.setLocation(getRandomLocation());
        restaurant = getRestaurant(order.getLocation());
    }

    @Override
    protected void takeProducts() {
        OrderManager orderManager = OrderManager.getInstance();
        int count = 0;

        while (count < 10) {
            Product p = orderManager.printAndSelectProducts(restaurant, customer);
            if (p == null) break;

            orderedProducts.add(p);
            quantities.add(1);
            count++;
        }
    }

    @Override
    protected void printOrder() {
        System.out.println("\n===== FINAL ORDER =====\n");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Phone: " + customer.getPhone());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("\nProducts ordered:\n");

        for (int i = 0; i < orderedProducts.size(); i++) {
            Product product = orderedProducts.get(i);
            int quantity = quantities.get(i);

            System.out.println((i + 1) + ". " + product.getName() + " x" + quantity);

            if (product instanceof PizzaDecorator deco) {
                Pizza basePizza = deco.getBasePizza();
                System.out.println("   Base ingredients: " + basePizza.getIngredients());

                List<String> extras =
                        ((ExtraIngredient) deco).getAllExtraIngredients();
                if (!extras.isEmpty())
                    System.out.println("   Extra ingredients: " + String.join(", ", extras));
            }
            else if (product instanceof Pizza pizza) {
                System.out.println("   Ingredients: " + pizza.getIngredients());
            }
            System.out.println();
        }
    }

    // ---- helpers ----

    private Restaurant getRestaurant(Location loc) {
        return switch (loc) {
            case BARCELONA -> new BarcelonaRestaurant();
            case GIRONA -> new GironaRestaurant();
            case LLEIDA -> new LleidaRestaurant();
            case TARRAGONA -> new TarragonaRestaurant();
        };
    }

    private Location getRandomLocation() {
        return Location.values()[new Random().nextInt(Location.values().length)];
    }
}
