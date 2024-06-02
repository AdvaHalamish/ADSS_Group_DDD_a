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
        storage.insertItem(new Item("Strawberry", 8.0, "Berry Co.", "S004", "Fruits", "Fresh", 0.5, ItemPlace.Store, LocalDate.now().plusDays(3), ItemStatus.Available));
        storage.insertItem(new Item("Orange", 12.0, "Fruit Co.", "O005", "Fruits", "Fresh", 1.0, ItemPlace.Store, LocalDate.now().plusDays(7), ItemStatus.Available));
        storage.insertItem(new Item("Tomato", 5.0, "Veggie Farms", "T006", "Vegetables", "Fresh", 0.3, ItemPlace.Store, LocalDate.now().plusDays(2), ItemStatus.Available));
        storage.insertItem(new Item("Carrot", 6.0, "Veggie Farms", "C007", "Vegetables", "Fresh", 0.4, ItemPlace.Store, LocalDate.now().plusDays(4), ItemStatus.Available));
        storage.insertItem(new Item("Eggplant", 7.0, "Veggie Farms", "E008", "Vegetables", "Fresh", 0.6, ItemPlace.Warehouse, LocalDate.now().plusDays(8), ItemStatus.Available));
        storage.insertItem(new Item("Cheese", 25.0, "Dairy Farms", "C009", "Dairy", "Cheese", 3.0, ItemPlace.Store, LocalDate.now().plusDays(6), ItemStatus.Available));
        storage.insertItem(new Item("Yogurt", 18.0, "Dairy Farms", "Y010", "Dairy", "Yogurt", 1.5, ItemPlace.Store, LocalDate.now().plusDays(3), ItemStatus.Available));
        storage.insertItem(new Item("Bread", 10.0, "Bakery Co.", "B011", "Bakery", "Bread", 1.0, ItemPlace.Store, LocalDate.now().plusDays(5), ItemStatus.Available));
        storage.insertItem(new Item("Grapes", 9.0, "Vineyard Co.", "G012", "Fruits", "Fresh", 0.7, ItemPlace.Store, LocalDate.now().plusDays(4), ItemStatus.Available));
        storage.insertItem(new Item("Watermelon", 20.0, "Melon Farms", "W013", "Fruits", "Fresh", 5.0, ItemPlace.Warehouse, LocalDate.now().plusDays(8), ItemStatus.Available));
        storage.insertItem(new Item("Pineapple", 12.0, "Tropical Fruits Inc.", "P014", "Fruits", "Fresh", 2.0, ItemPlace.Store, LocalDate.now().plusDays(3), ItemStatus.Available));
        storage.insertItem(new Item("Cucumber", 7.0, "Veggie Farms", "C015", "Vegetables", "Fresh", 0.5, ItemPlace.Store, LocalDate.now().plusDays(2), ItemStatus.Available));
        storage.insertItem(new Item("Lettuce", 5.0, "Veggie Farms", "L016", "Vegetables", "Fresh", 0.3, ItemPlace.Store, LocalDate.now().plusDays(3), ItemStatus.Available));
        storage.insertItem(new Item("Potato", 4.0, "Veggie Farms", "P017", "Vegetables", "Fresh", 0.2, ItemPlace.Store, LocalDate.now().plusDays(5), ItemStatus.Available));
        storage.insertItem(new Item("Spinach", 6.0, "Veggie Farms", "S018", "Vegetables", "Fresh", 0.4, ItemPlace.Store, LocalDate.now().plusDays(4), ItemStatus.Available));
        storage.insertItem(new Item("Chicken", 30.0, "Poultry Farms", "CH019", "Meat", "Poultry", 1.0, ItemPlace.Warehouse, LocalDate.now().plusDays(10), ItemStatus.Available));
        storage.insertItem(new Item("Beef", 40.0, "Cattle Farms", "B020", "Meat", "Beef", 2.0, ItemPlace.Warehouse, LocalDate.now().plusDays(12), ItemStatus.Available));
        storage.insertItem(new Item("Pork", 35.0, "Pig Farms", "P021", "Meat", "Pork", 1.5, ItemPlace.Warehouse, LocalDate.now().plusDays(9), ItemStatus.Available));
        storage.insertItem(new Item("Grapes", 9.0, "Vineyard Co.", "G022", "Fruits", "Fresh", 0.7, ItemPlace.Store, LocalDate.now().plusDays(7), ItemStatus.Available));
        storage.insertItem(new Item("Watermelon", 20.0, "Melon Farms", "W023", "Fruits", "Fresh", 5.0, ItemPlace.Warehouse, LocalDate.now().plusDays(12), ItemStatus.Available));
        storage.insertItem(new Item("Pineapple", 12.0, "Tropical Fruits Inc.", "P024", "Fruits", "Fresh", 2.0, ItemPlace.Store, LocalDate.now().plusDays(6), ItemStatus.Available));
        storage.insertItem(new Item("Cucumber", 7.0, "Veggie Farms", "C025", "Vegetables", "Fresh", 0.5, ItemPlace.Store, LocalDate.now().plusDays(8), ItemStatus.Available));
        storage.insertItem(new Item("Lettuce", 5.0, "Veggie Farms", "L026", "Vegetables", "Fresh", 0.3, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Available));
        storage.insertItem(new Item("Potato", 4.0, "Veggie Farms", "P027", "Vegetables", "Fresh", 0.2, ItemPlace.Store, LocalDate.now().plusDays(11), ItemStatus.Available));
        storage.insertItem(new Item("Spinach", 6.0, "Veggie Farms", "S028", "Vegetables", "Fresh", 0.4, ItemPlace.Store, LocalDate.now().plusDays(9), ItemStatus.Available));
        storage.insertItem(new Item("Chicken", 30.0, "Poultry Farms", "CH029", "Meat", "Poultry", 1.0, ItemPlace.Warehouse, LocalDate.now().plusDays(15), ItemStatus.Available));
        storage.insertItem(new Item("Beef", 40.0, "Cattle Farms", "B030", "Meat", "Beef", 2.0, ItemPlace.Warehouse, LocalDate.now().plusDays(13), ItemStatus.Available));
        storage.insertItem(new Item("Pork", 35.0, "Pig Farms", "P031", "Meat", "Pork", 1.5, ItemPlace.Warehouse, LocalDate.now().plusDays(14), ItemStatus.Available));

    }

    // Method to get the storage
    public static Storage getStorage() {
        initializeData();
        return storage;
    }
}
