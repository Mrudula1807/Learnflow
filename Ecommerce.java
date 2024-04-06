import java.util.*;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class ShoppingCart {
    private Map<Product, Integer> items = new HashMap<>();

    public void addToCart(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
}

class Order {
    private ShoppingCart cart;
    private User user;

    public Order(ShoppingCart cart, User user) {
        this.cart = cart;
        this.user = user;
    }

    public void displayOrderSummary() {
        System.out.println("Order Summary for User: " + user.getUsername());
        double totalAmount = 0;
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = product.getPrice() * quantity;
            System.out.println(product.getName() + " x " + quantity + " : $" + subtotal);
            totalAmount += subtotal;
        }
        System.out.println("Total Amount: $" + totalAmount);
    }
}

class ECommercePlatform {
    public List<Product> products = new ArrayList<>();
    public List<User> users = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public Order checkout(User user, ShoppingCart cart) {
        if (user == null) {
            System.out.println("User not authenticated. Please login first.");
            return null;
        }

        Order order = new Order(cart, user);
        order.displayOrderSummary();
        return order;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ECommercePlatform platform = new ECommercePlatform();

        // Add products
        platform.addProduct(new Product("Laptop", 1000));
        platform.addProduct(new Product("Phone", 500));

        // Register users
        platform.registerUser(new User("user1", "password1"));
        platform.registerUser(new User("user2", "password2"));

        // User login
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = platform.authenticateUser(username, password);
        if (user == null) {
            System.out.println("Authentication failed.");
            return;
        }
        System.out.println("Authentication successful.");

        // Display products
        System.out.println("Available Products:");
        for (Product product : platform.products) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }

        // Add items to cart
        ShoppingCart cart = new ShoppingCart();
        while (true) {
            System.out.print("Enter product name to add to cart (or type 'done' to finish): ");
            String productName = scanner.nextLine();
            if (productName.equalsIgnoreCase("done")) {
                break;
            }
            Product productToAdd = null;
            for (Product product : platform.products) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    productToAdd = product;
                    break;
                }
            }
            if (productToAdd == null) {
                System.out.println("Product not found.");
                continue;
            }
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            cart.addToCart(productToAdd, quantity);
        }

        // Checkout
        Order order = platform.checkout(user, cart);
        if (order != null) {
            // Implement payment processing here
            System.out.println("Payment processed successfully.");
        }
    }
}
