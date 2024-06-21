package Utils;

import Classes.*;

import java.time.LocalDate;

public class Database {
    private static Storage storage;

    private static void initializeData() {
        storage = new Storage();

        // Create and insert products
        Product apple = new Product("Apple Inc.", "Fruits", "Apple", "Fresh", "A001", 1.0, 2.0, 10, 5, ItemPlace.Store, LocalDate.now().minusDays(1));
        Product banana = new Product("Fruit Co.", "Fruits", "Banana", "Fresh", "B002", 1.0, 3.0, 20, 5, ItemPlace.Warehouse, LocalDate.now().plusDays(10));
        Product milk = new Product("Dairy Farms", "Dairy", "Milk", "Milk", "M003", 1.0, 4.0, 30, 5, ItemPlace.Store, LocalDate.now().plusDays(5));
        Product cheese = new Product("Dairy Farms", "Dairy", "Cheese", "Cheese", "C004", 1.0, 5.0, 25, 5, ItemPlace.Store, LocalDate.now().plusDays(6));
        Product yogurt = new Product("Dairy Farms", "Dairy", "Yogurt", "Yogurt", "Y005", 1.0, 3.0, 15, 5, ItemPlace.Store, LocalDate.now().plusDays(3));
        Product bread = new Product("Bakery Co.", "Bakery", "Bread", "Bread", "B006", 1.0, 2.0, 20, 5, ItemPlace.Store, LocalDate.now().plusDays(5));

        // Insert products into storage
        storage.insertProduct(apple);
        storage.insertProduct(banana);
        storage.insertProduct(milk);
        storage.insertProduct(cheese);
        storage.insertProduct(yogurt);
        storage.insertProduct(bread);
    }

    // Method to get the storage
    public static Storage getStorage() {
        initializeData();
        return storage;
    }
}
