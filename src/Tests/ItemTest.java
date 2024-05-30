package Tests;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import Classes.*;
public class ItemTest {

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
        assertEquals(20.0, item.getSellingPrice());
    }

    @Test
    public void testSetAndGetExpirationDate() {
        Item item = new Item("Item", 10.0, "Manufacturer", "Code", "Category", "SubCategory", 1.0, ItemPlace.Store, LocalDate.now().plusDays(10), ItemStatus.Available);
        LocalDate newDate = LocalDate.now().plusDays(5);
        item.setExpirationDate(newDate);
        assertEquals(newDate, item.getExpirationDate());
    }
}

