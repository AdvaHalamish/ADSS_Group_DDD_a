package Tests;

import java.time.LocalDate;
import Classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class StorageTest {
    private Storage storage;
    private Item item1;
    private Item item2;
    private Item item3;


    @BeforeEach
    public void setUp() {
        storage = new Storage();
        item1 = new Item("Item1", 10.0, "Manufacturer1", "Code1", "Category1", "SubCategory1", 1.0, ItemPlace.Store, LocalDate.now().minusDays(1), ItemStatus.Available);
        item2 = new Item("Item2", 15.0, "Manufacturer2", "Code2", "Category1", "SubCategory1", 1.0, ItemPlace.Warehouse, LocalDate.now().plusDays(10), ItemStatus.Defective);
        item3 = new Item("Item3", 20.0, "Manufacturer3", "Code3", "Category2", "SubCategory2", 2.0, ItemPlace.Store, LocalDate.now().plusDays(5), ItemStatus.Available);
    }

    @Test
    public void testInsertItemAndGetProductsBySubCategory() {
        storage.insertItem(item1);
        storage.insertItem(item2);
        storage.insertItem(item3);

        List<Product> products = storage.getProductsBySubCategory("SubCategory1");
        assertEquals(1, products.size());
        assertEquals("Category1", products.get(0).getCategory());
        assertEquals("SubCategory1", products.get(0).getSubCategory());
    }

    @Test
    public void testGenerateExpiredProductsReport() {
        storage.insertItem(item1);
        storage.insertItem(item2);
        storage.insertItem(item3);

        List<Item> expiredItems = storage.generateExpiredProductsReport();
        assertEquals(1, expiredItems.size());
        assertEquals("Item1", expiredItems.get(0).getName());
    }

    @Test
    public void testGenerateDefectiveProductsReport() {
        storage.insertItem(item1);
        storage.insertItem(item2);
        storage.insertItem(item3);

        List<Item> defectiveItems = storage.generateDefectiveProductsReport();
        assertEquals(1, defectiveItems.size());
        assertEquals("Item2", defectiveItems.get(0).getName());
    }

    @Test
    public void testGetProductName() {
        storage.insertItem(item1);
        assertEquals("Item1", storage.getProductByName(item1.getItem_name()).getProductName());
    }

    @Test
    public void testGetQuantityInStore() {
        storage.insertItem(item1);
        assertEquals(1,storage.TotalQuantityInStore());
    }

    @Test
    public void testGetQuantityInWarehouse() {
        storage.insertItem(item2);
        assertEquals(1,storage.TotalQuantityInWareHouse());
    }

    @Test
    public void testRemoveItem() {
        storage.insertItem(item2);
        assertEquals(1, storage.TotalQuantity());
        storage.removeItem(item2.getItem_code(),ItemStatus.Defective);
        assertEquals(0, storage.TotalQuantity());
    }

    @Test
    public void testGetTotalQuantity() {
        storage.insertItem(item1);
        storage.insertItem(item2);
        assertEquals(2, storage.getProductByName(item1.getItem_name()).getTotalQuantity());
    }


}