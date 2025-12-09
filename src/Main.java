import Enums.Location;
import Models.Customer;
import Models.Order;
import Models.Product;
import Models.Restaurant;

import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Restaurant restBCN = new Restaurant(Location.BARCELONA);
        Restaurant restGRN = new Restaurant(Location.GIRONA);
        Restaurant rest1LLE = new Restaurant(Location.LLEIDA);
        Restaurant rest1TAR = new Restaurant(Location.TARRAGONA);
    }

    public static void takeOrder() {
        System.out.println("Before taking the order, please give us your details:");
        Customer c = createCustomerFromInput();
        Order order = new Order();

        order.setLocation(getRandomLocation());
        order.setCustomer(c);

        int itemCount = 0;
        boolean orderComplete = false
        Scanner s = new Scanner(System.in);
        while (itemCount < 10 && !orderComplete) {
            System.out.print("What product do you want? Enter number: ");
            printAvailableProducts(order.getLocation());
            Product p = numberToProduct(new Product(s.nextLine()));
            order.addProduct(p);
            itemCount++;
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
    public static void printAvailableProducts(Location location) {

            System.out.println("===== PIZZAS =====");

            System.out.println("1. Margherita");
            System.out.println("   Ingredients: Tomato & mozzarella\n");

            System.out.println("2. Hawaiian");
            System.out.println("   Ingredients: Ham & pineapple\n");

            System.out.println("3. Bacon Crispy");
            System.out.println("   Ingredients: Ham, chicken & bacon\n");

            System.out.println("4. American");
            System.out.println("   Ingredients: Frankfurt, bacon & egg\n");

            System.out.println("5. Traviata");
            System.out.println("   Ingredients: Bacon, sausage & onion\n");

            System.out.println("6. Burger");
            System.out.println("   Ingredients: Miniburgers, egg, bacon & onion\n");

            System.out.println("7. Castellera");
            System.out.println("   Ingredients: Onion, tuna, peperoni & olives\n");

            System.out.println("8. Cowboy");
            System.out.println("   Ingredients: BBQ Sauce, cheddar, roquefort cheese & bacon\n");

            System.out.println("9. Texas");
            System.out.println("   Ingredients: BBQ Sauce, onion, tomato slices & chicken\n");

            System.out.println("10. Coast");
            System.out.println("    Ingredients: Tuna, anchovies, prawns & pineapple\n");

            System.out.println("11. BBQ");
            System.out.println("    Ingredients: BBQ Sauce, beef, bacon & chicken\n");

            System.out.println("12. Diablo");
            System.out.println("    Ingredients: Ham, beef, bacon & chicken\n");

            System.out.println("13. Carbonara");
            System.out.println("    Ingredients: Carbonara sauce, bacon, onion & mushrooms\n");

            System.out.println("14. Spanish");
            System.out.println("    Ingredients: Jamón Serrano, brie, olives & mushrooms\n");

            System.out.println("15. Four Cheeses");
            System.out.println("    Ingredients: Mozzarella, emmental, roquefort & cheddar\n");

            System.out.println("16. Pepperoni");
            System.out.println("    Ingredients: Pepperoni, ham, beef & bacon\n");

            System.out.println("17. Vegetal");
            System.out.println("    Ingredients: Onion, bell pepper, tomato slices, artichoke & mushrooms\n");
            switch (location) {
                case BARCELONA:
                    System.out.println("18. Barcelona");
                    System.out.println("    Ingredients: Onion, beef, brie, ham & olives\n");
                    break;
                case GIRONA:
                    System.out.println("18. Girona");
                    System.out.println("    Ingredients: Beef, ham, mushrooms, chicken & olives\n");
                    break;
                case LLEIDA:
                    System.out.println("18. Lleida");
                    System.out.println("    Ingredients: BBQ Sauce, beef, chicken, mushrooms & olives\n");
                    break;
                case TARRAGONA:
                    System.out.println("18. Tarragona");
                    System.out.println("    Ingredients: Tuna, prawns, onion, ham & olives\n");

                    break;
            }

            System.out.println("19. Six Cheeses");
            System.out.println("    Ingredients: Mozzarella, goat cheese, brie, emmental, roquefort & cheddar\n");

            System.out.println("20. Mallorca");
            System.out.println("    Ingredients: Goat cheese, emmental, cheddar, brie, sobrassada & olives\n");

            System.out.println("21. Carbonara Deluxe");
            System.out.println("    Ingredients: Carbonara sauce, bacon, onion, mushrooms, goat cheese & honey\n");

            System.out.println("===== BEVERAGES =====");
            System.out.println("22. Water");
            System.out.println("23. Beer");
            System.out.println("24. Soda\n");
        }

    }
}
