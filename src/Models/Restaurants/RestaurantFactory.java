package Models.Restaurants;

import Enums.Location;

// PATTERN: FACTORY PATTERN (restaurants)
// Through this factory the creation of restaurants is centralized.
// Whenever any restaurant is created is done through here, reducing code duplication.
// To add a new king of restaurant we just add a class and add it in the switch.

// Thanks to the Restaurant abstract class, we are always returning an object of that class

public class RestaurantFactory {
    public static Restaurant getRestaurant(Location loc) {
        return switch (loc) {
            case BARCELONA -> new BarcelonaRestaurant();
            case GIRONA -> new GironaRestaurant();
            case LLEIDA -> new LleidaRestaurant();
            case TARRAGONA -> new TarragonaRestaurant();
        };
    }

}
