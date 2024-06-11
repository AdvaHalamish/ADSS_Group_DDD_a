package Menus;

import Classes.Item;
import Classes.ItemStatus;
import Classes.Product;
import Classes.Storage;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WorkerMenu {
    private Storage storage;

    public WorkerMenu(Storage storage) {
        this.storage = storage;
    }

    public void displayMenu(Scanner scanner) {
        int choice=0;
        do {
            System.out.println("\nWorker Menu");
            System.out.println("1. Update Item Status");
            System.out.println("2. Generate Report for Products Below Minimum Quantity");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer choice.");
                scanner.nextLine(); // Consume invalid input
                continue; // Continue to next iteration of the loop
            }

            switch (choice) {
                case 1:
                    deleteItem(scanner);
                    break;
                case 2:
                    generateBelowMinimumReport();
                    break;
                case 3:
                    System.out.println("Exiting Worker Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
    private void deleteItem(Scanner scanner) {
        System.out.print("Enter item code to remove: ");
        String code = scanner.nextLine();
        System.out.print("Enter new status to the item: (Defective, Soldout, Expired) ");
        String status_string = scanner.nextLine();
        if (isValidStatus(status_string)) {
            ItemStatus status = ItemStatus.valueOf(status_string);
            if (storage.removeItem(code, status)) {
                System.out.println("Item Status changed.");
            } else {
                System.out.println("Item Not Found.");
            }
        } else {
            System.out.println("Status Not Valid.");
        }
    }
    private static boolean isValidStatus(String statusInput) {
        for (ItemStatus status : ItemStatus.values()) {
            if (status.name().equals(statusInput)) {
                return true;
            }
        }
        return false;
    }


    private void generateBelowMinimumReport() {
        System.out.println("Products below minimum quantity:");
        List<Product> products = storage.generateBelowMinimumReport();
        for (Product product : products) {
            System.out.println(product.getProductName());
        }
    }
}
