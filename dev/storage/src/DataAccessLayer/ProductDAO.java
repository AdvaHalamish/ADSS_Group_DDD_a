package DataAccessLayer;

import BuisnessLayer.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDAO {
    private Connection conn;

    public ProductDAO(Connection connection) throws SQLException {
        this.conn = Database.connect(); // Assuming Database class manages connection
    }

    public boolean insert(Product product) {
        try {
            String sql = "INSERT INTO products (manufacturer, category, productName, subCategory, productCode, costPrice, sellingPrice, size, status, minimumQuantityForAlert) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, product.getManufacturer());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getProductName());
            stmt.setString(4, product.getSubCategory());
            stmt.setString(5, product.getProductCode());
            stmt.setDouble(6, product.getPurchasePrice());
            stmt.setDouble(7, product.getSellingPrice());
            stmt.setDouble(8, product.getSize());
            stmt.setString(9, product.getStatus().toString());
            stmt.setInt(10, product.getMinimumQuantityForAlert());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                String productId = generatedKeys.getString(1);
                product.setProductId(productId); // Assuming you have a method to set product ID in Product class
            }

            // Automatically add items for the product
            addItemsForProduct(product);

            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting product: " + e.getMessage());
            return false;
        }
    }

    private void addItemsForProduct(Product product) throws SQLException {
        int addQuantity = product.getTotalQuantity(); // Adjust based on how you determine the quantity to add
        ItemPlace itemPlace = ItemPlace.Store; // Adjust based on your logic
        LocalDate expirationDate = LocalDate.now().plusYears(1); // Example expiration date
        ItemStatus itemStatus = ItemStatus.Available; // Adjust based on your logic

        String itemCodePrefix = product.getProductCode() + "-";

        for (int i = 0; i < addQuantity; i++) {
            String itemCode = itemCodePrefix + i;
            Item newItem = new Item(itemPlace, itemCode, expirationDate, itemStatus);
            insertItem(newItem, product.getProductCode());
            product.getItems().put(newItem.getItemCode(), newItem);
        }
    }

    private void insertItem(Item item, String productCode) throws SQLException {
        String sql = "INSERT INTO items (itemCode, productCode, itemPlace, expirationDate, itemStatus) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, item.getItemCode());
        stmt.setString(2, productCode);
        stmt.setString(3, item.getStored().toString());
        stmt.setString(4, item.getExpirationDate().toString());
        stmt.setString(5, item.getStatus().toString());
        stmt.executeUpdate();
    }
    public Product getProductByCode(String productCode) {
        try {
            String sql = "SELECT * FROM products WHERE productCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, productCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Extract data from ResultSet
                String manufacturer = rs.getString("manufacturer");
                String category = rs.getString("category");
                String productName = rs.getString("productName");
                String subCategory = rs.getString("subCategory");
                double costPrice = rs.getDouble("costPrice");
                double size = rs.getDouble("size");
                int quantity = rs.getInt("quantity");
                int minimumQuantity = rs.getInt("minimumQuantityForAlert");
                LocalDate expirationDate = rs.getDate("expirationDate").toLocalDate();

                // Create Product object
                Product product = new Product(manufacturer, category, productName, subCategory, productCode,
                        costPrice, size, quantity, minimumQuantity, null, expirationDate); // Replace null with ItemPlace as needed

                // Add items to the product
                product.addItems(quantity, null, expirationDate, ItemStatus.Available); // Replace null with ItemPlace as needed

                return product;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving product: " + e.getMessage());
        }
        return null;
    }
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products WHERE category = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Extract product data from result set and create Product objects
                Product product = extractProductFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products by category: " + e.getMessage());
        }
        return products;
    }

    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        String manufacturer = rs.getString("manufacturer");
        String category = rs.getString("category");
        String productName = rs.getString("productName");
        String subCategory = rs.getString("subCategory");
        double costPrice = rs.getDouble("costPrice");
        double sellingPrice = rs.getDouble("sellingPrice");
        double size = rs.getDouble("size");
        ProductStatus status = ProductStatus.valueOf(rs.getString("status"));
        int minimumQuantityForAlert = rs.getInt("minimumQuantityForAlert");

        return new Product(manufacturer, category, productName, subCategory, rs.getString("productCode"),
                costPrice, size, 0, minimumQuantityForAlert, null, null);
    }

    private HashMap<String, Item> getItemsForProduct(String productCode) throws SQLException {
        HashMap<String, Item> items = new HashMap<>();
        String sql = "SELECT * FROM items WHERE productCode = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, productCode);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Item item = new Item(ItemPlace.valueOf(rs.getString("itemPlace")), rs.getString("itemCode"),
                    LocalDate.parse(rs.getString("expirationDate")), ItemStatus.valueOf(rs.getString("itemStatus")));
            items.put(item.getItemCode(), item);
        }

        return items;
    }

    public boolean update(Product product) {
        try {
            String sql = "UPDATE products SET manufacturer = ?, category = ?, productName = ?, subCategory = ?, " +
                    "costPrice = ?, sellingPrice = ?, size = ?, status = ?, minimumQuantityForAlert = ? " +
                    "WHERE productCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getManufacturer());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getProductName());
            stmt.setString(4, product.getSubCategory());
            stmt.setDouble(5, product.getPurchasePrice());
            stmt.setDouble(6, product.getSellingPrice());
            stmt.setDouble(7, product.getSize());
            stmt.setString(8, product.getStatus().toString());
            stmt.setInt(9, product.getMinimumQuantityForAlert());
            stmt.setString(10, product.getProductCode());
            stmt.executeUpdate();

            // Update items associated with the product
            updateItemsForProduct(product);

            return true;
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
            return false;
        }
    }

    private void updateItemsForProduct(Product product) throws SQLException {
        // Remove all items and re-add them for simplicity in this example
        deleteItemsForProduct(product.getProductCode());
        addItemsForProduct(product);
    }

    private void deleteItemsForProduct(String productCode) throws SQLException {
        String sql = "DELETE FROM items WHERE productCode = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, productCode);
        stmt.executeUpdate();
    }

    public boolean delete(String productCode) {
        try {
            // Delete items first
            deleteItemsForProduct(productCode);

            // Then delete the product
            String sql = "DELETE FROM products WHERE productCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, productCode);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
