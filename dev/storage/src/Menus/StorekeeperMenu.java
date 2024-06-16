package Menus;

import Classes.Item;
import Classes.ItemStatus;
import Classes.Storage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StorekeeperMenu {
    private Storage storage;

    public StorekeeperMenu(Storage storage) {
        this.storage = storage;
    }

    public void displayMenu(Scanner scanner) {
        int choice = 0;
        do {
            System.out.println("\nStorekeeper Menu");
            System.out.println("1. Update Item Status");
            System.out.println("2. Exit");
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
                    updateItemStatus(scanner);
                    break;
                case 2:
                    System.out.println("Exiting Storekeeper Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 2);
    }

    private void updateItemStatus(Scanner scanner) {
        System.out.print("Enter item code to update: ");
        String code = scanner.nextLine();
        System.out.print("Enter new status for the item: (Defective, Sold, Expired) ");
        String statusString = scanner.nextLine();
        if (isValidStatus(statusString)) {
            ItemStatus status = ItemStatus.valueOf(statusString);
            if (storage.removeItem(code, status)) {
                System.out.println("Item status updated.");
            } else {
                System.out.println("Item not found.");
            }
        } else {
            System.out.println("Invalid status.");
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
}
