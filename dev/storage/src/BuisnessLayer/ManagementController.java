package BuisnessLayer;

import DataAccessLayer.ItemDAO;
import DataAccessLayer.ProductDAO;
import java.util.List;

public class ManagementController {
    private ProductDAO productDAO;
    private ItemDAO itemDAO;

    public ManagementController(ProductDAO productDAO, ItemDAO itemDAO) {
        this.productDAO = productDAO;
        this.itemDAO = itemDAO;
    }

    public List<Product> generateCategoryReport(String category) {
        // Generate category report using DAO
        return productDAO.getProductsByCategory(category);
    }

    public List<Item> generateExpiredProductsReport() {
        // Generate expired products report using DAO
        return itemDAO.getExpiredItems();
    }

    // Add other management-related functions as needed
}
