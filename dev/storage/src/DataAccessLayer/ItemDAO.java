package DataAccessLayer;

import BuisnessLayer.Item;
import BuisnessLayer.ItemPlace;
import BuisnessLayer.ItemStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Connection conn;

    public ItemDAO(Connection connection) throws SQLException {
        // Initialize connection to database
        conn = Database.connect();
    }

    public boolean insert(Item item, String productCode) {
        try {
            String sql = "INSERT INTO items (itemCode, itemPlace, expirationDate, itemStatus, productCode) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getItemCode());
            stmt.setString(2, item.getItemPlace().toString());
            stmt.setString(3, item.getExpirationDate().toString());
            stmt.setString(4, item.getItemStatus().toString());
            stmt.setString(5, productCode);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception occurred in insert ItemDAO: " + e.getMessage());
        }
        return false;
    }

    public Item getItemByCode(String itemCode) {
        try {
            String sql = "SELECT * FROM items WHERE itemCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, itemCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ItemPlace itemPlace = ItemPlace.valueOf(rs.getString("itemPlace"));
                LocalDate expirationDate = LocalDate.parse(rs.getString("expirationDate"));
                ItemStatus itemStatus = ItemStatus.valueOf(rs.getString("itemStatus"));
                String productCode = rs.getString("productCode");
                return new Item(itemPlace, itemCode, expirationDate, itemStatus);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in getItemByCode ItemDAO: " + e.getMessage());
        }
        return null;
    }

    public boolean update(String itemCode, ItemStatus status) {
        try {
            String sql = "UPDATE items SET status = ? WHERE itemCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status.toString()); // Assuming status is stored as a string in the database
            stmt.setString(2, itemCode);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Exception occurred in update ItemDAO: " + e.getMessage());
            return false;
        }
    }
    public List<Item> getExpiredItems() {
        List<Item> expiredItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String sql = "SELECT * FROM items WHERE expirationDate < ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            rs = stmt.executeQuery();

            while (rs.next()) {
                Item item = new Item(
                        ItemPlace.valueOf(rs.getString("stored")),
                        rs.getString("itemCode"),
                        rs.getDate("expirationDate").toLocalDate(),
                        ItemStatus.valueOf(rs.getString("status"))
                );
                expiredItems.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching expired items: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return expiredItems;
    }

    public boolean delete(String itemCode) {
        try {
            String sql = "DELETE FROM items WHERE itemCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, itemCode);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception occurred in delete ItemDAO: " + e.getMessage());
        }
        return false;
    }

    public void close() {
        // Close connection
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred while closing connection in ItemDAO: " + e.getMessage());
        }
    }
}
