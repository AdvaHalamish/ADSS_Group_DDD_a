package Menus;

import Classes.Item;
import Classes.ItemStatus;
import Classes.Product;
import Classes.Storage;
import java.util.Scanner;

public class UserMenu {
    private static Storage storage;

    public UserMenu(Storage storage) {
        this.storage = storage;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
                System.out.println("\nMain Menu:");
                System.out.println("1. Show all items");
                System.out.println("2. Show items in categories");
                System.out.println("3. Details about a specific item");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        showAvailableProducts();
                        break;
                    case 2:
                        showItemsInCategoriesMenu(scanner);
                        break;
                    case 3:
                        showProductDetails(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 3);
            scanner.close();
        }
    private void showAvailableProducts() {
        System.out.println("Available products in storage:");
        for (Product product : storage.getAllProducts()) {
            for (Item item : product.getItems().values()) {
                if (item.getStatus() == ItemStatus.Available) {
                    System.out.println(item.getName());
                }
            }
        }
    }
    private static void showItemsInCategoriesMenu(Scanner scanner) {
        System.out.println("\nShow items in categories:");
        System.out.println("1. Category");
        System.out.println("2. Sub-category");
        System.out.println("3. Size");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                showItemsByCategory(scanner);
                break;
            case 2:
                showItemsBySubCategory(scanner);
                break;
            case 3:
                showItemsBySize(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    private static void showItemsByCategory(Scanner scanner) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.println("\nItems in category " + category + ":");
        for (Product product : storage.getAllProducts()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                for (Item item : product.getItems().values()) {
                    System.out.println(item);
                }
            }
        }
    }
    private static void showItemsBySubCategory(Scanner scanner) {
        System.out.print("Enter sub-category: ");
        String subCategory = scanner.nextLine();
        System.out.println("\nItems in sub-category " + subCategory + ":");
        for (Product product : storage.getAllProducts()) {
            if (product.getSubCategory().equalsIgnoreCase(subCategory)) {
                for (Item item : product.getItems().values()) {
                    System.out.println(item);
                }
            }
        }
    }
    private static void showItemsBySize(Scanner scanner) {
        System.out.print("Enter size: ");
        double size = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.println("\nItems with size " + size + ":");
        for (Product product : storage.getAllProducts()) {
            for (Item item : product.getItems().values()) {
                if (item.getSize() == size) {
                    System.out.println(item);
                }
            }
        }
    }
    private void showProductDetails(Scanner scanner) {
        System.out.print("Enter Product name: ");
        String ProductName = scanner.nextLine();
        Product product = storage.getProductByName(ProductName);
        if (product != null) {
            System.out.println("Product details: " + product);
        } else {
            System.out.println("Product not found.");
        }
    }
}