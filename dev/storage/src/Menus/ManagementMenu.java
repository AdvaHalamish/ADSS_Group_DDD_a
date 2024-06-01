package Menus;

import Classes.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ManagementMenu {
    private Storage storage;

    public ManagementMenu(Storage storage) {
        this.storage = storage;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nManagement Menu");
            System.out.println("1. Show All Items");
            System.out.println("2. Show All Products");
            System.out.println("3. Show items in categories");
            System.out.println("4. Show items by status");
            System.out.println("5. Show items by place");
            System.out.println("6. Details about a specific item");
            System.out.println("7. Apply Discount");
            System.out.println("8. Generate Report");
            System.out.println("9. View Total Amount in Storage");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    showAllItems();
                    break;
                case 2:
                    showAllProducts();
                    break;
                case 3:
                    showItemsInCategories(scanner);
                    break;
                case 4:
                    showItemsByStatus(scanner);
                    break;
                case 5:
                    showItemsByPlace(scanner);
                    break;
                case 6:
                    showItemDetails(scanner);
                    break;
                case 7:
                    // Apply Discount
                    applyDiscountMenu(scanner);
                    break;
                case 8:
                    // Generate Report
                    generateReportMenu(scanner);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                case 10:
                    displayTotalAmountInStorage();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                    break;
            }
        } while (choice != 10);
    }

    private void showAllItems() {
        List<Product> allProducts = storage.getAllProducts();
        allProducts.forEach(product -> {
            System.out.println("Product: " + product.getProductName());
            product.getItems().values().forEach(item -> System.out.println("  " + item));
        });
    }
    private void showAllProducts() {
        System.out.println("\nAll Products In Storage:");
        for (Product product : storage.getAllProducts()) {
            if(product.getStatus().equals(ProductStatus.InStorage)) {
                System.out.println("Product: " + product.getProductName());
                System.out.println("Category: " + product.getCategory());
                System.out.println("Sub-category: " + product.getSubCategory());
                System.out.println("Size: " + product.getSize());
                System.out.println("Quantity in Store: " + product.getQuantityInStore());
                System.out.println("Quantity in Warehouse: " + product.getQuantityInWarehouse());
                System.out.println("Minimum Quantity for Alert: " + product.getMinimumQuantityForAlert());
                System.out.println("Discount: " + (product.getDiscount() != null ? "Active" : "Not Active"));
                System.out.println(); // Empty line between products
            }
        }
    }

    private void showItemsInCategories(Scanner scanner) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        List<Product> products = storage.getProductsByCategory(category);
        products.forEach(product -> {
            System.out.println("Product: " + product.getProductName());
            product.getItems().values().forEach(item -> System.out.println("  " + item));
        });
    }

    private void showItemsByStatus(Scanner scanner) {
        System.out.print("Enter status (Available, Defective, Soldout, Expired): ");
        String statusStr = scanner.nextLine();
        ItemStatus status = ItemStatus.valueOf(statusStr);
        List<Item> items = storage.getItemsByStatus(status);
        items.forEach(item -> System.out.println("Item: " + item));
    }

    private void showItemsByPlace(Scanner scanner) {
        System.out.print("Enter place (Store, Warehouse): ");
        String placeStr = scanner.nextLine();
        ItemPlace place = ItemPlace.valueOf(placeStr);
        List<Item> items = storage.getItemsByPlace(place);
        items.forEach(item -> System.out.println("Item: " + item));
    }

    private void showItemDetails(Scanner scanner) {
        System.out.print("Enter item code: ");
        String itemCode = scanner.nextLine();
        Item item = storage.getItemByCode(itemCode);
        if (item != null) {
            System.out.println("Item details: " + item);
        } else {
            System.out.println("Item not found.");
        }
    }

    private void applyDiscountToCategory(Scanner scanner) {
        System.out.print("Enter category to apply discount: ");
        String category = scanner.nextLine();
        System.out.print("Enter discount rate (e.g., 0.1 for 10%): ");
        double rate = scanner.nextDouble();
        System.out.print("Enter discount start date (YYYY-MM-DD): ");
        String startDateStr = scanner.next();
        LocalDate startDate = LocalDate.parse(startDateStr);
        System.out.print("Enter discount end date (YYYY-MM-DD): ");
        String endDateStr = scanner.next();
        LocalDate endDate = LocalDate.parse(endDateStr);

        Discount discount = new Discount(rate, startDate, endDate);
        storage.applyDiscountToCategory(category, discount);
        System.out.println("Discount applied to category " + category);
    }

    private void applyDiscountToProduct(Scanner scanner) {
        System.out.print("Enter product name to apply discount: ");
        String productName = scanner.nextLine();
        System.out.print("Enter discount rate (e.g., 0.1 for 10%): ");
        double rate = scanner.nextDouble();
        System.out.print("Enter discount start date (YYYY-MM-DD): ");
        String startDateStr = scanner.next();
        LocalDate startDate = LocalDate.parse(startDateStr);
        System.out.print("Enter discount end date (YYYY-MM-DD): ");
        String endDateStr = scanner.next();
        LocalDate endDate = LocalDate.parse(endDateStr);

        Discount discount = new Discount(rate, startDate, endDate);
        storage.applyDiscountToProduct(productName, discount);
        System.out.println("Discount applied to product " + productName);
    }

    private void generateReportForSpecificCategory(Scanner scanner) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        List<Product> products = storage.generateCategoryReport(category);
        products.forEach(product -> {
            System.out.println("Product: " + product.getProductName());
            product.getItems().values().forEach(item -> System.out.println("  " + item));
        });
    }

    private void generateReportForCategories(Scanner scanner) {
        System.out.print("Enter categories (comma-separated): ");
        String[] categories = scanner.nextLine().split(",");
        for (String category : categories) {
            List<Product> products = storage.generateCategoryReport(category.trim());
            System.out.println("Category: " + category);
            products.forEach(product -> {
                System.out.println("  Product: " + product.getProductName());
                product.getItems().values().forEach(item -> System.out.println("    " + item));
            });
        }
    }
    private void applyDiscountMenu(Scanner scanner) {
        System.out.println("\nApply Discount");
        System.out.println("1. Apply Discount to Category");
        System.out.println("2. Apply Discount to Product");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                applyDiscountToCategory(scanner);
                break;
            case 2:
                applyDiscountToProduct(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void generateReportMenu(Scanner scanner) {
        System.out.println("\nGenerate Report");
        System.out.println("1. Generate Report for Specific Category");
        System.out.println("2. Generate Report for Categories");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                generateReportForSpecificCategory(scanner);
                break;
            case 2:
                generateReportForCategories(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    public void displayTotalAmountInStorage() {
        int totalQuantity = storage.TotalQuantity();
        int totalQuantityInStore = storage.TotalQuantityInStore();
        int totalQuantityInWarehouse = storage.TotalQuantityInWareHouse();

        System.out.println("Total Amount in Storage:");
        System.out.println("Total Quantity: " + totalQuantity);
        System.out.println("Total Quantity in Store: " + totalQuantityInStore);
        System.out.println("Total Quantity in Warehouse: " + totalQuantityInWarehouse);
    }
}