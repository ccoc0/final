package restaurantpos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * DatabaseHandler - Handles all database operations for the Restaurant POS System
 * Uses MySQL database (compatible with XAMPP)
 *
 * @author RestaurantPOS
 */
public class DatabaseHandler {

    // MySQL Database Connection Settings (XAMPP default)
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "restaurant_pos";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static DatabaseHandler instance;
    private Connection connection;

    // Admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    private DatabaseHandler() {
        initializeDatabase();
    }

    public static synchronized DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    private void initializeDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect without database to create it if needed
            String urlWithoutDB = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            Connection tempConnection = DriverManager.getConnection(urlWithoutDB, DB_USER, DB_PASSWORD);

            Statement createDBStmt = tempConnection.createStatement();
            createDBStmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            createDBStmt.close();
            tempConnection.close();

            // Connect to the database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            createTables();
            // Menu items should be imported from SQL file via phpMyAdmin

            System.out.println("Database connected successfully!");

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            JOptionPane.showMessageDialog(null,
                    "MySQL JDBC Driver not found!\nPlease add MySQL Connector/J to your project library.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Could not connect to MySQL database!\n\nMake sure:\n1. XAMPP is running\n2. MySQL service is started\n\nError: " + e.getMessage(),
                    "Database Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createTables() throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("CREATE TABLE IF NOT EXISTS menu_items (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "category VARCHAR(50) NOT NULL, " +
                "price DECIMAL(10, 2) NOT NULL, " +
                "description TEXT, " +
                "image_path VARCHAR(255), " +
                "available TINYINT DEFAULT 1)");

        stmt.execute("CREATE TABLE IF NOT EXISTS buyer_history (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "buyer_name VARCHAR(100) NOT NULL, " +
                "items_ordered TEXT NOT NULL, " +
                "total_amount DECIMAL(10, 2) NOT NULL, " +
                "order_date DATETIME NOT NULL, " +
                "payment_method VARCHAR(50), " +
                "status VARCHAR(20) DEFAULT 'Completed')");

        stmt.execute("CREATE TABLE IF NOT EXISTS orders (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "menu_item_id INT, " +
                "quantity INT DEFAULT 1, " +
                "order_date DATETIME NOT NULL, " +
                "FOREIGN KEY (menu_item_id) REFERENCES menu_items(id))");

        stmt.close();
    }

    public boolean authenticateAdmin(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        try {
            if (connection == null || connection.isClosed()) {
                System.err.println("Database connection is closed!");
                return items;
            }
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM menu_items WHERE available = 1 OR available IS NULL ORDER BY category, name");
            while (rs.next()) {
                items.add(new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image_path")
                ));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error fetching menu items: " + e.getMessage());
        }
        return items;
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        List<MenuItem> items = new ArrayList<>();
        try {
            if (connection == null || connection.isClosed()) return items;
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM menu_items WHERE category = ? AND (available = 1 OR available IS NULL) ORDER BY name");
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                items.add(new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image_path")
                ));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return items;
    }

    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        try {
            if (connection == null || connection.isClosed()) return categories;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT category FROM menu_items WHERE available = 1 ORDER BY category");
            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return categories;
    }

    public boolean addMenuItem(MenuItem item) {
        try {
            if (connection == null || connection.isClosed()) return false;
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO menu_items (name, category, price, description, image_path) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getCategory());
            pstmt.setDouble(3, item.getPrice());
            pstmt.setString(4, item.getDescription());
            pstmt.setString(5, item.getImagePath());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean updateMenuItem(MenuItem item) {
        try {
            if (connection == null || connection.isClosed()) return false;
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE menu_items SET name = ?, category = ?, price = ?, description = ?, image_path = ? WHERE id = ?");
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getCategory());
            pstmt.setDouble(3, item.getPrice());
            pstmt.setString(4, item.getDescription());
            pstmt.setString(5, item.getImagePath());
            pstmt.setInt(6, item.getId());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteMenuItem(int id) {
        try {
            if (connection == null || connection.isClosed()) return false;
            // Hard delete - permanently remove from database
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM menu_items WHERE id = ?");
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    // ================== BUYER HISTORY METHODS ==================

    public List<BuyerHistory> getAllBuyerHistory() {
        List<BuyerHistory> history = new ArrayList<>();
        try {
            if (connection == null || connection.isClosed()) return history;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM buyer_history ORDER BY order_date DESC");
            while (rs.next()) {
                history.add(new BuyerHistory(
                        rs.getInt("id"),
                        rs.getString("buyer_name"),
                        rs.getString("items_ordered"),
                        rs.getDouble("total_amount"),
                        rs.getString("order_date"),
                        rs.getString("payment_method"),
                        rs.getString("status")
                ));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return history;
    }

    public boolean addBuyerHistory(BuyerHistory history) {
        try {
            if (connection == null || connection.isClosed()) return false;
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO buyer_history (buyer_name, items_ordered, total_amount, order_date, payment_method, status) VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, history.getBuyerName());
            pstmt.setString(2, history.getItemsOrdered());
            pstmt.setDouble(3, history.getTotalAmount());
            pstmt.setString(4, history.getOrderDate());
            pstmt.setString(5, history.getPaymentMethod());
            pstmt.setString(6, history.getStatus());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public List<BuyerHistory> searchBuyerHistory(String searchTerm) {
        List<BuyerHistory> history = new ArrayList<>();
        try {
            if (connection == null || connection.isClosed()) return history;
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM buyer_history WHERE buyer_name LIKE ? OR items_ordered LIKE ? ORDER BY order_date DESC");
            String pattern = "%" + searchTerm + "%";
            pstmt.setString(1, pattern);
            pstmt.setString(2, pattern);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                history.add(new BuyerHistory(
                        rs.getInt("id"),
                        rs.getString("buyer_name"),
                        rs.getString("items_ordered"),
                        rs.getDouble("total_amount"),
                        rs.getString("order_date"),
                        rs.getString("payment_method"),
                        rs.getString("status")
                ));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return history;
    }

    public double getTotalSalesToday() {
        double total = 0;
        try {
            if (connection == null || connection.isClosed()) return total;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(total_amount) as total FROM buyer_history WHERE DATE(order_date) = CURDATE()");
            if (rs.next()) total = rs.getDouble("total");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return total;
    }

    public int getTotalOrdersToday() {
        int count = 0;
        try {
            if (connection == null || connection.isClosed()) return count;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM buyer_history WHERE DATE(order_date) = CURDATE()");
            if (rs.next()) count = rs.getInt("count");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return count;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}