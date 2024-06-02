package Utils;

import Classes.Item;
import Classes.ItemPlace;
import Classes.ItemStatus;
import Classes.Storage;

import java.time.LocalDate;

public class Database {
    private static Storage storage;

    private static void initializeData() {
        // Initialize storage with some items and products
        storage = new Storage();
        storage.insertItem(new Item("Apple", 10.0, "Apple Inc.", "A001", "Fruits", "Fresh", 1.0, ItemPlace.Store, LocalDate.now().minusDays(1), ItemStatus.Available));
        storage.insertItem(new Item("Banana", 15.0, "Fruit Co.", "B002", "Fruits", "Fresh", 1.0, ItemPlace.Warehouse, LocalDate.now().plusDays(10), ItemStatus.Defective));
        storage.insertItem(new Item("Milk", 20.0, "Dairy Farms", "M003", "Dairy", "Milk", 2.0, ItemPlace.Store, LocalDate.now().plusDays(5), ItemStatus.Available));
        // Add more items as needed
    }

    // Method to get the storage
    public static Storage getStorage() {
        initializeData();
        return storage;
    }
}
