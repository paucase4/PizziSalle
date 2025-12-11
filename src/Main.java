import Enums.Location;
import Models.*;
import Models.Restaurants.*;

import java.util.ArrayList;
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

        order.setLocation(getRandomLocation());
        order.setCustomer(c);

        Restaurant rest = getRestaurant(order.getLocation());
        int itemCount = 0;
        boolean orderComplete = false;
        Scanner s = new Scanner(System.in);
        while (itemCount < 10 && !orderComplete) {
            System.out.print("What product do you want? Enter number: ");
            printAndSelectProducts(rest);
            //  Product p = numberToProduct(new Product(s.nextLine()));
            //  order.addProduct(p);
            itemCount+=15;
        }
    }

    public static Customer createCustomerFromInput() {
        Scanner sc = new Scanner(System.in);
        Customer c = new Customer();

        System.out.print("Enter the customer's name: ");
        c.setName(sc.nextLine());

        System.out.print("Enter the phone number: ");
        c.setPhone(sc.nextLine());

        System.out.print("Enter the address: ");
        c.setAddress(sc.nextLine());

        System.out.print("Is this their first time buying? (y/n): ");
        if (sc.nextLine().charAt(0) == 'y') c.setFirst(true);
        else c.setFirst(false);


        System.out.println("Customer created successfully.");
        return c;
    }

    public static Location getRandomLocation() {
        return Location.values()[new Random().nextInt(Location.values().length)];
    }
    public static String printAndSelectProducts(Restaurant rest) {

            ArrayList<Product> products = (ArrayList<Product>) rest.getAvailableProducts();
            System.out.println("\n\n===== PIZZAS =====\n\n");
            int i = 1;
            for (Product p: products) {
                System.out.println(i + ". " + p.getName());
                if (p instanceof Pizza) {
                    System.out.println(" (Ingredients: " + ((Pizza)p).getIngredients() + ")");
                } else {
                    System.out.println();
                }
                i++;
            }
            Scanner scanner = new Scanner(System.in);
            int choice = -1;

            while (choice < 1 || choice > products.size()) {
                System.out.print("\nChoose pizza number: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
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

            return selectedProduct.getName();



        }

    }

