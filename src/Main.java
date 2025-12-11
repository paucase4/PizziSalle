import Enums.Beverage;
import Enums.Ingredient;
import Enums.Location;
import Models.*;
import Models.Decorator.ExtraIngredient;
import Models.Decorator.PizzaDecorator;
import Models.Restaurants.*;

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

        order.setLocation(getRandomLocation());
        order.setCustomer(c);

        Restaurant rest = getRestaurant(order.getLocation());

        int itemCount = 0;
        boolean orderComplete = false;
        ArrayList<Product> orderedProducts = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();

        while (itemCount < 10) {
            System.out.print("\nWhat product do you want? Enter number (if your order is complete type -1): ");

            Product p = printAndSelectProducts(rest, c);
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

            //double subtotal = product.getPrice() * quantity;
            //System.out.println("   Price per unit: $" + String.format("%.2f", product.getPrice()));
            //System.out.println("   Subtotal: $" + String.format("%.2f", subtotal));
            System.out.println();

         //   totalPrice += subtotal;
        }

        System.out.println("Total items: " + itemCount);
        //System.out.println("TOTAL PRICE: $" + String.format("%.2f", totalPrice));
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
    public static Product printAndSelectProducts(Restaurant rest, Customer c) {
            ArrayList<Product> products = (ArrayList<Product>) rest.getAvailableProducts();
            products.sort((p1, p2) -> {
                boolean p1IsBeverage = p1 instanceof Beverage;
                boolean p2IsBeverage = p2 instanceof Beverage;

                if (p1IsBeverage && !p2IsBeverage) return 1;
                if (!p1IsBeverage && p2IsBeverage) return -1;
                return 0;
            });
            boolean bevs = false;
            System.out.println("\n\n===== PIZZAS =====\n\n");
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
        if (selectedProduct instanceof Pizza) {
                Pizza orderedPizza = new Pizza(selectedProduct.getName(), ((Pizza)selectedProduct).getIngredients());
                return addExtraIngredients((Pizza)orderedPizza, scanner);

        }
            return selectedProduct;



        }

  /*    // Ask for quantity
        int quantity = getQuantity(scanner);

        System.out.println("\nFinal order: " + selectedProduct.getName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Price per unit: $" + String.format("%.2f", selectedProduct.getPrice()));
        System.out.println("Total price: $" + String.format("%.2f", selectedProduct.getPrice() * quantity));

        return selectedProduct; // new OrderItem(selectedProduct, quantity);
    }
*/
    private static Product addExtraIngredients(Product pizza, Scanner scanner) {
        System.out.println("\nAvailable extra ingredients:");
        Ingredient[] availableIngredients = Ingredient.values();

        for (int i = 0; i < availableIngredients.length; i++) {
            System.out.println((i + 1) + ". " + availableIngredients[i]);
        }


        System.out.print("\nDo you want to add extra ingredients? (yes/no): ");
        String response = scanner.next().toLowerCase();

        if (!response.equals("yes") && !response.equals("y")) {
            return pizza;
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
//            System.out.println("Price: $" + String.format("%.2f", decoratedPizza.getPrice()));
        }

        return decoratedPizza;
    }
    private static int getQuantity(Scanner scanner) {
        int quantity = -1;

        while (quantity < 1 || quantity > 10) {
            System.out.print("\nEnter quantity (1-10): ");
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                if (quantity < 1 || quantity > 10) {
                    System.out.println("Quantity must be between 1 and 10.");
                }
            } else {
                System.out.println("Invalid input.");
                scanner.next();
            }
        }

        return quantity;
    }
    }



