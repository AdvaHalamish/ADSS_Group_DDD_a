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
            System.out.println("1. Update Item Status");
            System.out.println("2. Generate Report for Products Below Minimum Quantity");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
    //TODO:: checking the String from user- and throw exceptions
    private void deleteItem(Scanner scanner) {
        System.out.print("Enter item code to remove: ");
        String code = scanner.nextLine();
        System.out.print("Enter new status to the item: (Defective, Soldout, Expired) ");
        String status = scanner.nextLine();
        if(storage.removeItem(code, ItemStatus.valueOf(status)))
            System.out.println("Item Status changed.");
        else
            System.out.println("Item Not Found.");

        }

    private void generateBelowMinimumReport() {
        System.out.println("Products below minimum quantity:");
        List<Product> products = storage.generateBelowMinimumReport();
        for (Product product : products) {
            System.out.println(product.getProductName());
        }
    }
}
