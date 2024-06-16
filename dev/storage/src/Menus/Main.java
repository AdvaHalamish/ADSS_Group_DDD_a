package Menus;

import Classes.Storage;
import Utils.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Storage storage;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice=0;
        storage= Storage.getInstance();
        initializeSystem();

        do {
            System.out.println("\nMain Menu");
            System.out.println("1. Worker Menu");
            System.out.println("2. Management Menu");
            System.out.println("3. User Menu");
            System.out.println("4. Exit");
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
                    StorekeeperMenu storekeeperMenu = new StorekeeperMenu(storage);
                    storekeeperMenu.displayMenu(scanner);
                    break;
                case 2:
                    ManagementMenu departmentMenu = new ManagementMenu(storage);
                    departmentMenu.displayMenu(scanner);
                    break;
                case 3:
                    UserMenu userMenu = new UserMenu(storage);
                    userMenu.displayMenu(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private static void initializeSystem() {
        storage = Database.getStorage();
    }
}
