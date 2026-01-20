package com.java17.interview.prepartion;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class Order {
        private String restaurantName;
        private String city;
        private String dishName;
        private double price;

        public Order(String restaurantName, String city, String dishName, double price) {
                this.restaurantName = restaurantName;
                this.city = city;
                this.dishName = dishName;
                this.price = price;
        }

        public String getRestaurantName() {
                return restaurantName;
        }

        public String getCity() {
                return city;
        }

        public String getDishName() {
                return dishName;
        }

        public double getPrice() {
                return price;
        }
}

public class Top5RestaurantsBySell {

        public static void main(String[] args) {

                List<Order> orders = List.of(
                                new Order("Pizza Hut", "Bangalore", "Margherita Pizza", 500),
                                new Order("Dominos", "Delhi", "Veggie Paradise", 300),
                                new Order("KFC", "Mumbai", "Zinger Burger", 700),
                                new Order("Burger King", "Hyderabad", "Whopper", 400),
                                new Order("Subway", "Bangalore", "Veggie Delight", 250),
                                new Order("Biryani Blues", "Delhi", "Hyderabadi Biryani", 800),
                                new Order("Behrouz Biryani", "Pune", "Mughlai Biryani", 650),
                                new Order("Dominos", "Delhi", "Farmhouse Pizza", 200),
                                new Order("Pizza Hut", "Bangalore", "Paneer Pizza", 1000),
                                new Order("Burger King", "Hyderabad", "Crispy Veg", 900),
                                new Order("Biryani Blues", "Delhi", "Chicken Biryani", 1200),
                                new Order("Subway", "Bangalore", "Paneer Tikka Sub", 450),
                                new Order("KFC", "Mumbai", "Hot Wings", 600),
                                new Order("McDonald's", "Pune", "McSpicy Paneer", 950),
                                new Order("McDonald's", "Pune", "McAloo Tikki", 500),
                                new Order("Behrouz Biryani", "Pune", "Royal Chicken Biryani", 850),
                                new Order("Taco Bell", "Chennai", "Cheese Taco", 750),
                                new Order("Taco Bell", "Chennai", "Veg Burrito", 550),
                                new Order("Haldiram's", "Delhi", "Thali", 650),
                                new Order("Haldiram's", "Delhi", "Samosa Plate", 350));

                // 🏆 PROBLEM 1: Top 5 Restaurants by Total Revenue
                // Step 1: Group by restaurant (across all cities) and sum their revenue
                Map<String, Double> totalRevenue = orders.stream()
                                .collect(Collectors.groupingBy(
                                                Order::getRestaurantName,
                                                Collectors.summingDouble(Order::getPrice)));


                /***
                 *
                 * When to Use Which
                 *
                 * Choose one depending on what you are sorting:
                 *
                 * Sort Criteria	Correct Code
                 * Sort by Key descending	.sorted(Map.Entry.<K, V>comparingByKey().reversed())
                 * Sort by Value descending	.sorted(Map.Entry.<K, V>comparingByValue(Comparator.reverseOrder()))
                 *
                 * Example:
                 *
                 * 1️⃣ Sort by Key descending:
                 * .sorted(Map.Entry.<Integer, List<String>>comparingByKey().reversed())
                 *
                 * 2️⃣ Sort by Value descending:
                 * .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                 */

                // Step 2: Sort by revenue descending and pick top 5
                List<Map.Entry<String, Double>> top5 = totalRevenue.entrySet().stream()
                        //  .sorted(Map.Entry.<Integer, List<String>>comparingByKey().reversed())
                                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                                .limit(5)
                                .toList();

                // Step 3: Print top 5 restaurants by revenue
                System.out.println("🏆 Top 5 Restaurants by Total Revenue:");
                top5.forEach(entry -> System.out.println(entry.getKey() + " => ₹" + entry.getValue()));

                // 🏙️ PROBLEM 2: Total Revenue Per City
                // Group orders by city and calculate total revenue for each city
                System.out.println("\n🏙️ Total Revenue Per City:");
                Map<String, Double> revenuePerCity = orders.stream()
                                .collect(Collectors.groupingBy(
                                                Order::getCity,
                                                Collectors.summingDouble(Order::getPrice)));
                revenuePerCity.forEach((city, revenue) -> System.out.println(city + " => ₹" + revenue));

                // 🍛 PROBLEM 3: Top Restaurant Per City
                // For each city, find the restaurant that earned the highest revenue
                System.out.println("\n🍛 Top Restaurant Per City:");
                Map<String, Optional<Map.Entry<String, Double>>> topRestaurantPerCity = orders.stream()
                                .collect(Collectors.groupingBy(
                                                Order::getCity,
                                                Collectors.collectingAndThen(
                                                                Collectors.groupingBy(Order::getRestaurantName,
                                                                                Collectors.summingDouble(
                                                                                                Order::getPrice)),
                                                                map -> map.entrySet().stream()
                                                                                .max(Map.Entry.comparingByValue()))));

                topRestaurantPerCity.forEach((city, top) -> System.out
                                .println(city + " => " + top.get().getKey() + " (₹" + top.get().getValue() + ")"));

                // 🍔 PROBLEM 4: Most Popular Dish Overall
                // Find the dish that was ordered the most (by count)
                System.out.println("\n🍔 Most Popular Dish Overall:");
                Map.Entry<String, Long> mostPopularDish = orders.stream()
                                .collect(Collectors.groupingBy(Order::getDishName, Collectors.counting()))
                                .entrySet().stream()
                                .max(Map.Entry.comparingByValue())
                                .orElseThrow();

                System.out.println("Most ordered dish: " + mostPopularDish.getKey() + " (" + mostPopularDish.getValue()
                                + " orders)");

                // 👨‍🍳 PROBLEM 5: Total Orders Per Restaurant
                // Find how many orders each restaurant has served
                System.out.println("\n👨‍🍳 Total Orders Per Restaurant:");
                Map<String, Long> orderCountPerRestaurant = orders.stream()
                                .collect(Collectors.groupingBy(Order::getRestaurantName, Collectors.counting()));
                orderCountPerRestaurant.forEach(
                                (restaurant, count) -> System.out.println(restaurant + " => " + count + " orders"));

                // ⏱️ PROBLEM 6: Average Order Value Per Restaurant
                // Find average order value per restaurant
                System.out.println("\n⏱️ Average Order Value Per Restaurant:");
                Map<String, Double> avgOrderValue = orders.stream()
                                .collect(Collectors.groupingBy(
                                                Order::getRestaurantName,
                                                Collectors.averagingDouble(Order::getPrice)));
                avgOrderValue.forEach((restaurant, avg) -> System.out
                                .println(restaurant + " => ₹" + String.format("%.2f", avg)));

                // 🧾 PROBLEM 7: Sort Restaurants Alphabetically and Print Total Orders
                // Print all restaurants in alphabetical order with their total order counts
                System.out.println("\n🧾 Restaurants Alphabetically with Order Counts:");
                orders.stream()
                                .collect(Collectors.groupingBy(Order::getRestaurantName, Collectors.counting()))
                                .entrySet().stream()
                                .sorted(Map.Entry.comparingByKey())
                                .forEach(e -> System.out.println(e.getKey() + " => " + e.getValue() + " orders"));

                // 🧍‍♀️ PROBLEM 8: Filter Orders Above ₹500 and List Restaurant Names
                // Print all unique restaurant names that have received orders above ₹500
                System.out.println("\n🧍‍♀️ Restaurants with Orders Above ₹500:");
                orders.stream()
                                .filter(o -> o.getPrice() > 500)
                                .map(Order::getRestaurantName)
                                .distinct()
                                .forEach(System.out::println);

                // 🗓️ PROBLEM 9: Find Restaurants That Serve Specific Dish
                // List all restaurants that serve "Biryani"
                System.out.println("\n🗓️ Restaurants Serving Biryani:");
                orders.stream()
                                .filter(o -> o.getDishName().toLowerCase().contains("biryani"))
                                .map(Order::getRestaurantName)
                                .distinct()
                                .forEach(System.out::println);

                // ⏰ PROBLEM 10: Count Orders Per City (Sorted Desc)
                // Find cities with most number of total orders
                System.out.println("\n⏰ Cities by Order Count (Descending):");
                orders.stream()
                                .collect(Collectors.groupingBy(Order::getCity, Collectors.counting()))
                                .entrySet().stream()
                                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                                .forEach(e -> System.out.println(e.getKey() + " => " + e.getValue() + " orders"));

                // 🎯 PROBLEM 11: Highest Priced Single Order (per City)
                // Find the most expensive single order placed in each city
                System.out.println("\n🎯 Highest Priced Order Per City:");
                Map<String, Optional<Order>> highestOrderPerCity = orders.stream()
                                .collect(Collectors.groupingBy(
                                                Order::getCity,
                                                Collectors.maxBy(Comparator.comparingDouble(Order::getPrice))));
                highestOrderPerCity.forEach((city, order) -> {
                        if (order.isPresent()) {
                                Order o = order.get();
                                System.out.println(city + " => " + o.getDishName() + " from " + o.getRestaurantName()
                                                + " (₹" + o.getPrice() + ")");
                        }
                });

                // 🧮 PROBLEM 12: Convert Orders to a Custom DTO
                // Convert each order into a custom summary object
                System.out.println("\n🧮 Order Summaries (Custom DTO Format):");
                List<String> orderSummaries = orders.stream()
                                .map(o -> o.getCity() + " | " + o.getRestaurantName() + " | " + o.getDishName() + " | ₹"
                                                + o.getPrice())
                                .toList();
                orderSummaries.forEach(System.out::println);

                // ⚙️ PROBLEM 13: Group by City, Then by Restaurant (Nested Map)
                // Group orders by city and within city by restaurant
                System.out.println("\n⚙️ Nested Grouping: City -> Restaurant -> Orders:");
            Map<String, Map<String, List<Order>>> cityRestaurantMap = orders.stream()
                    .collect(Collectors.groupingBy(
                            Order::getCity, // group by City
                            Collectors.groupingBy(Order::getRestaurantName) // inside each city -> group by Restaurant
                    ));

            /**
             *
             *
             * {
             *   Delhi = {
             *       Dominos = [Order1, Order2],
             *       Pizza Hut = [Order3]
             *   },
             *   Mumbai = {
             *       KFC = [Order4]
             *   }
             * }
             *
             *
             * orders.stream()
             *    ↓ group into cities
             * City A:
             *    ↓ group into restaurants
             *      Restaurant X → List<Order>
             *      Restaurant Y → List<Order>
             *
             * City B:
             *    ↓ group into restaurants
             *      Restaurant Z → List<Order>
             */

            // Print the result
            cityRestaurantMap.forEach((city, restaurantMap) -> {
                System.out.println(city + ":");
                restaurantMap.forEach((restaurant, orderList) ->
                        System.out.println("  " + restaurant + " => " + orderList.size() + " orders")
                );
            });

            Map<String, Map<String, Long>> cityRestaurantCount = orders.stream()
                    .collect(Collectors.groupingBy(
                            Order::getCity,
                            Collectors.groupingBy(Order::getRestaurantName, Collectors.counting())
                    ));


        }

}