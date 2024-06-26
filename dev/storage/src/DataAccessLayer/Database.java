package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            String createProductsTable = "CREATE TABLE IF NOT EXISTS products (\n"
                    + " product_code TEXT PRIMARY KEY,\n"
                    + " manufacturer TEXT NOT NULL,\n"
                    + " category TEXT NOT NULL,\n"
                    + " product_name TEXT NOT NULL,\n"
                    + " sub_category TEXT NOT NULL,\n"
                    + " purchase_price REAL NOT NULL,\n"
                    + " selling_price REAL NOT NULL,\n"
                    + " min_quantity INTEGER NOT NULL,\n"
                    + " quantity_in_store INTEGER NOT NULL,\n"
                    + " item_place TEXT NOT NULL,\n"
                    + " expiration_date TEXT NOT NULL\n"
                    + ");";

            String createItemsTable = "CREATE TABLE IF NOT EXISTS items (\n"
                    + " item_code TEXT PRIMARY KEY,\n"
                    + " product_code TEXT NOT NULL,\n"
                    + " status TEXT NOT NULL,\n"
                    + " FOREIGN KEY (product_code) REFERENCES products (product_code)\n"
                    + ");";

            stmt.execute(createProductsTable);
            stmt.execute(createItemsTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database");
        }
        return connection;
    }
}
