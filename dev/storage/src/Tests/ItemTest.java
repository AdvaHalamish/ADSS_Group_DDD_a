package Tests;
import java.util.ArrayList;
import java.util.List;
//import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import Classes.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void testIsExpired() {
        Item expiredItem = new Item("ExpiredItem", 10.0, "Manufacturer1", "Code1", "Category1", "SubCategory1", 1.0, ItemPlace.Store, LocalDate.now().minusDays(1), ItemStatus.Available);
        assertTrue(expiredItem.isExpired());

        Item nonExpiredItem = new Item("NonExpiredItem", 15.0, "Manufacturer2", "Code2", "Category1", "SubCategory1", 1.0, ItemPlace.Warehouse, LocalDate.now().plusDays(10), ItemStatus.Available);
        assertFalse(nonExpiredItem.isExpired());
    }

    @Test
    public void testSetAndGetSellingPrice() {
        Item item = new Item("Item", 10.0, "Manufacturer", "Code", "Category", "SubCategory", 1.0, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Available);
        item.setSellingPrice(20.0);
        assertEquals(20.0, item.getSellingPrice(),0);
    }

    @Test
    public void testSetAndGetExpirationDate() {
        Item item = new Item("Item", 10.0, "Manufacturer", "Code", "Category", "SubCategory", 1.0, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Available);
        LocalDate newDate = LocalDate.now().plusDays(5);
        item.setExpirationDate(newDate);
        assertEquals(newDate, item.getExpirationDate());
    }
    @Test
    public void testGetItemCode() {
        Item item = new Item("Item", 10.0, "Manufacturer", "Code123", "Category", "SubCategory", 1.0, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Available);
        assertEquals("Code123", item.getItem_code());
    }

    @Test
    public void testGetItemStatus() {
        Item item = new Item("Item", 10.0, "Manufacturer", "Code123", "Category", "SubCategory", 1.0, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Defective);
        assertEquals(ItemStatus.Defective, item.getStatus());
    }

    @Test
    public void testSetPlace() {
        Item item = new Item("Item", 10.0, "Manufacturer", "Code123", "Category", "SubCategory", 1.0, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Available);
        item.set_place(ItemPlace.Warehouse);
        assertEquals(ItemPlace.Warehouse, item.getStored());
    }

    @Test
    public void testGetManufacturer() {
        Item item = new Item("Item", 10.0, "Manufacturer123", "Code123", "Category", "SubCategory", 1.0, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Available);
        assertEquals("Manufacturer123", item.getManufacturer());
    }

    @Test
    public void testToString() {
        Item item = new Item("Item", 10.0, "Manufacturer", "Code123", "Category", "SubCategory", 1.0, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Available);
        String expected = "Item_name='Item', size='1.0', sub category='SubCategory', category='Category', Item_code='Code123'";
        assertEquals(expected, item.toString());
    }

    @Test
    public void testGetCostPrice() {
        Item item = new Item("Item", 10.0, "Manufacturer", "Code123", "Category", "SubCategory", 1.0, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Available);
        assertEquals(10.0, item.getCostPrice(), 0);
    }

    @Test
    public void testGetCategory() {
        Item item = new Item("Item", 10.0, "Manufacturer", "Code123", "Category123", "SubCategory", 1.0, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Available);
        assertEquals("Category123", item.getCategory());
    }
}


