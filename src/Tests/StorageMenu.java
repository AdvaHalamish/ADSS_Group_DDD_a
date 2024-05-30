package Tests;

import java.time.LocalDate;
import java.util.Scanner;
import Classes.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import Tests.*;


public class StorageMenu {
    private static Storage storage = new Storage();

    public static void main(String[] args) {
        runTests();

        Scanner scanner = new Scanner(System.in);
        int choice;
        intelize_storage();
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Show all the items");
            System.out.println("2. Show items in categories");
            System.out.println("3. Show items by status");
            System.out.println("4. Details about a specific item");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showAllItemsMenu(scanner);
                    break;
                case 2:
                    showItemsInCategoriesMenu(scanner);
                    break;
                case 3:
                    showItemsByStatusMenu(scanner);
                    break;
                case 4:
                    showDetailsAboutSpecificItem(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }

    private static void intelize_storage() {
        Item expiredItem = new Item("ExpiredItem", 10.0, "Manufacturer1", "Code1", "Category1", "SubCategory1", 1.0, ItemPlace.Store, LocalDate.now().minusDays(1), ItemStatus.Available);
        Item nonExpiredItem = new Item("NonExpiredItem", 15.0, "Manufacturer2", "Code2", "Category1", "SubCategory1", 1.0, ItemPlace.Warehouse, LocalDate.now().plusDays(10), ItemStatus.Available);
        storage.insertItem(expiredItem);
        storage.insertItem(nonExpiredItem);

    }

    private static void showAllItemsMenu(Scanner scanner) {
        System.out.println("\nShow all the items:");
        System.out.println("1. All the Items");
        System.out.println("2. Show Items in the store");
        System.out.println("3. Show items in the warehouse");
        System.out.println("4. Shows Discount items");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                showAllItems();
                break;
            case 2:
                showItemsInStore();
                break;
            case 3:
                showItemsInWarehouse();
                break;
            case 4:
                showDiscountItems();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
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

    private static void showItemsByStatusMenu(Scanner scanner) {
        System.out.println("\nShow items by status:");
        System.out.println("1. Defective");
        System.out.println("2. Expired");
        System.out.println("3. Expired soon");
        System.out.println("4. Items on sale");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                showDefectiveItems();
                break;
            case 2:
                showExpiredItems();
                break;
            case 3:
                showItemsExpiringSoon();
                break;
            case 4:
                showProductOnSale();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void showDetailsAboutSpecificItem(Scanner scanner) {
        System.out.print("Choose category: ");
        String category = scanner.nextLine();
        System.out.print("Choose sub-category: ");
        String subCategory = scanner.nextLine();
        System.out.print("Choose size: ");
        double size = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.println("What do you want to know about this product?");
        System.out.println("1. Price (selling price and cost price)");
        System.out.println("2. Status");
        System.out.println("3. Consumer");
        System.out.println("4. Total amount (in the storage and in the warehouse)");
        System.out.println("5. Discount");
        System.out.println("6. Location");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = null;
        for (Product p : storage.getAllProducts()) {
            if (p.getCategory().equals(category) &&
                    p.getSubCategory().equals(subCategory) &&
                    p.getSize() == size) {
                product = p;
                break;
            }
        }

        if (product != null) {
            switch (choice) {
                case 1:
                    showProductPrices(product);
                    break;
                case 2:
                    showItemStatus(product);
                    break;
                case 3:
                    showItemConsumer(product);
                    break;
                case 4:
                    showTotalAmount(product);
                    break;
                case 5:
                    showItemDiscount(product);
                    break;
                case 6:
                    showItemLocation(product);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void showItemLocation(Product product) {
        System.out.println("\nLocation:");
        for (Item item : product.getItems().values()) {
            System.out.println("Product: " + product.getProductName() + ", Item: " + item.getItem_name() + ", Location: " + item.getStored());
        }
    }

    private static void showAllItems() {
        System.out.println("\nAll items:");
        for (Product product : storage.getAllProducts()) {
            for (Item item : product.getItems().values()) {
                System.out.println(item.toString());
            }
        }
    }

    private static void showItemsInStore() {
        System.out.println("\nItems in the store:");
        for (Product product : storage.getAllProducts()) {
            for (Item item : product.getItems().values()) {
                if (item.getStored() == ItemPlace.Store) {
                    System.out.println(item);
                }
            }
        }
    }

    private static void showItemsInWarehouse() {
        System.out.println("\nItems in the warehouse:");
        for (Product product : storage.getAllProducts()) {
            for (Item item : product.getItems().values()) {
                if (item.getStored() == ItemPlace.Warehouse) {
                    System.out.println(item);
                }
            }
        }
    }

    private static void showDiscountItems() {
        System.out.println("\nDiscount items:");
        for (Product product : storage.getAllProducts()) {
            if (product.getDiscount() != null && product.getDiscount().isDiscountActive()) {
                for (Item item : product.getItems().values()) {
                    System.out.println(item);
                }
            }
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

    private static void showDefectiveItems() {
        System.out.println("\nDefective items:");
        for (Product product : storage.getAllProducts()) {
            for (Item item : product.getItems().values()) {
                if (item.getStatus() == ItemStatus.Defective) {
                    System.out.println(item);
                }
            }
        }
    }

    private static void showExpiredItems() {
        System.out.println("\nExpired items:");
        for (Product product : storage.getAllProducts()) {
            for (Item item : product.getItems().values()) {
                if (item.isExpired()) {
                    System.out.println(item);
                }
            }
        }
    }

    private static void showItemsExpiringSoon() {
        System.out.println("\nItems expiring soon:");
        LocalDate soon = LocalDate.now().plusDays(7);
        for (Product product : storage.getAllProducts()) {
            for (Item item : product.getItems().values()) {
                if (item.getExpirationDate().isBefore(soon) && !item.isExpired()) {
                    System.out.println(item);
                }
            }
        }
    }

    private static void showProductOnSale() {
        System.out.println("\nProduct on sale:");
        for (Product product : storage.getAllProducts()) {
            if (product != null && product.getifDiscount()) {
                System.out.println(product);
            }
        }
    }

    private static void showProductPrices(Product product) {
        System.out.println("\nPrices:");
        for (Item item : product.getItems().values()) {
            System.out.println("Product: " + product.getProductName() + ", Selling Price: " + item.getSellingPrice() + ", Cost Price: " + item.getCostPrice());
        }
    }

    private static void showItemStatus(Product product) {
        System.out.println("\nStatus:");
        for (Item item : product.getItems().values()) {
            System.out.println("Product: " + product.getProductName() + ", Item: " + item.getItem_name() + ", Status: " + item.getStatus());
        }
    }

    private static void showItemConsumer(Product product) {
        System.out.println("\nConsumer:");
        for (Item item : product.getItems().values()) {
            System.out.println("Product: " + product.getProductName() + ", Item: " + item.getItem_name() + ", Consumer: " + item.getManufacturer());
        }
    }

    private static void showTotalAmount(Product product) {
        System.out.println("\nTotal amount:");
        int totalInStore = 0;
        int totalInWarehouse = 0;
        for (Item item : product.getItems().values()) {
            if (item.getStored() == ItemPlace.Store) {
                totalInStore++;
            } else if (item.getStored() == ItemPlace.Warehouse) {
                totalInWarehouse++;
            }
        }
        System.out.println("Total in Store: " + totalInStore + ", Total in Warehouse: " + totalInWarehouse);
    }

    private static void showItemDiscount(Product product) {
        System.out.println("\nDiscount:");
        if (product.getDiscount() != null && product.getDiscount().isDiscountActive()) {
            System.out.println("Product: " + product.getProductName() + ", Discount Rate: " + product.getDiscount().getDiscountRate());
        } else {
            System.out.println("Product: " + product.getProductName() + ", No active discount.");
        }
    }

    private static void runTests() {
        System.out.println("\nRunning tests...");
        Result result1 = JUnitCore.runClasses(ItemTest.class);
        Result result2 = JUnitCore.runClasses(ProductTest.class);
        Result result3 = JUnitCore.runClasses(StorageTest.class);
        if (result1.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Tests failed:");
            for (Failure failure : result1.getFailures()) {
                System.out.println(failure.toString());
            }
        }
        if (result2.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Tests failed:");
            for (Failure failure : result2.getFailures()) {
                System.out.println(failure.toString());
            }
        }
        if (result3.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Tests failed:");
            for (Failure failure : result3.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

}

