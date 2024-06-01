package Menus;

import Classes.Item;
import Classes.ItemStatus;
import Classes.Product;
import Classes.Storage;

import java.util.List;
import java.util.Scanner;

public class WorkerMenu {
    private Storage storage;

    public WorkerMenu(Storage storage) {
        this.storage = storage;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nWorker Menu");
            System.out.println("1. Update Defective Item");
            System.out.println("2. Update Sold out Item");
            System.out.println("3. Generate Report for Products Below Minimum Quantity");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    deleteItem(scanner);
                    break;
                case 2:
                    updateDefectiveItem(scanner);
                    break;
                case 3:
                    generateBelowMinimumReport();
                    break;
                case 4:
                    System.out.println("Exiting Worker Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private void deleteItem(Scanner scanner) {
        System.out.print("Enter item code to delete: ");
        String code = scanner.nextLine();
        storage.deleteItem(code);
        System.out.println("Item deleted.");
    }

    private void updateDefectiveItem(Scanner scanner) {
        System.out.print("Enter item code to update as defective: ");
        String code = scanner.nextLine();
        Item item = storage.getProductByName(code).getItems().get(code);
        if (item != null) {
            item.setStatus(ItemStatus.Defective);
            System.out.println("Item status updated to defective.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private void generateBelowMinimumReport() {
        System.out.println("Products below minimum quantity:");
        List<Product> products = storage.generateBelowMinimumReport();
        for (Product product : products) {
            System.out.println(product.getProductName());
        }
    }
}
