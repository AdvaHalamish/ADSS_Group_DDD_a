package Main;

import Classes.Storage;
import Menus.WorkerMenu;
import Menus.ManagementMenu;
import Menus.UserMenu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMain Menu");
            System.out.println("1. Worker Menu");
            System.out.println("2. Management Menu");
            System.out.println("3. User Menu");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    WorkerMenu workerMenu = new WorkerMenu(storage);
                    workerMenu.displayMenu();
                    break;
                case 2:
                    ManagementMenu departmentMenu = new ManagementMenu(storage);
                    departmentMenu.displayMenu();
                    break;
                case 3:
                    UserMenu userMenu = new UserMenu(storage);
                    userMenu.displayMenu();
                    break;
                case 4:
                    System.out.println("Exiting Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}
