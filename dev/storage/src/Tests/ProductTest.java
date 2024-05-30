package Tests;
import java.time.LocalDate;
import Classes.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product product;
    private Item item1;
    private Item item2;

    @BeforeEach
    public void setUp() {
        item1 = new Item("Item1", 10.0, "Manufacturer1", "Code1", "Category1", "SubCategory1", 1.0, ItemPlace.Store, LocalDate.now().minusDays(1), ItemStatus.Available);
        item2 = new Item("Item2", 15.0, "Manufacturer2", "Code2", "Category1", "SubCategory1", 1.0, ItemPlace.Warehouse, LocalDate.now().plusDays(10), ItemStatus.Defective);
        product = new Product(item1);

    }

    @Test
    public void testAddItem() {
        product.addItem(item2);
        assertEquals(2, product.getItems().size());
    }

    @Test
    public void testRemoveItem() {
        product.addItem(item2);
        product.removeItem(item1);
        assertEquals(1, product.getItems().size());
    }

    @Test
    public void testCheckQuantityAlert() {
        product.set_minimum(5);
        product.check_quantity();
        assertTrue(product.getTotalQuantity() < product.getMinimumQuantityForAlert());
    }

    @Test
    public void testApplyDiscount() {
        Discount discount = new Discount(0.1, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        product.applyDiscount(discount);
        assertEquals(9.0, product.getItems().get("Code1").getSellingPrice());
    }
    @Test
    public void testGetCategory() {
        assertEquals("Category1", product.getCategory());
    }

    @Test
    public void testGetSubCategory() {
        assertEquals("SubCategory1", product.getSubCategory());
    }

    @Test
    public void testGetSize() {
        assertEquals(1.0, product.getSize(), 0);
    }

    @Test
    public void testGetProductName() {
        assertEquals("Item1", product.getProductName());
    }

    @Test
    public void testGetQuantityInStore() {
        assertEquals(1, product.getQuantityInStore());
    }

    @Test
    public void testGetQuantityInWarehouse() {
        product.addItem(item2);
        assertEquals(1, product.getQuantityInWarehouse());
    }
}
