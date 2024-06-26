package BuisnessLayer;

import DataAccessLayer.ItemDAO;
import DataAccessLayer.ProductDAO;
import java.time.LocalDate;

public class StorageController {
    private ProductDAO productDAO;
    private ItemDAO itemDAO;

    public StorageController(ProductDAO productDAO, ItemDAO itemDAO) {
        this.productDAO = productDAO;
        this.itemDAO = itemDAO;
    }

    public void addItemsToProduct(String productCode, int quantity, ItemPlace itemPlace, LocalDate expirationDate, ItemStatus itemStatus) {
        // Retrieve product from DAO and add items
        Product product = productDAO.getProductByCode(productCode);
        if (product != null) {
            product.addItems(quantity, itemPlace, expirationDate, itemStatus);
            // Update product in DAO if needed
            productDAO.update(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    public boolean updateItemStatus(String itemCode, ItemStatus status) {
        // Update item status in DAO
        return itemDAO.update(itemCode, status);
    }
}
