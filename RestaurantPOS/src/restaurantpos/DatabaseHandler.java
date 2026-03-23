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
            insertDefaultMenuItems();
            
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
    
    private void insertDefaultMenuItems() throws SQLException {
        Statement checkStmt = connection.createStatement();
        ResultSet rs = checkStmt.executeQuery("SELECT COUNT(*) FROM menu_items");
        rs.next();
        if (rs.getInt(1) > 0) {
            rs.close();
            checkStmt.close();
            return;
        }
        rs.close();
        checkStmt.close();
        
        String insertSQL = "INSERT INTO menu_items (name, category, price, description, image_path) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(insertSQL);
        
        // Chicken
        insertMenuItem(pstmt, "Chickenjoy 1pc Solo", "Chicken", 99.00, "Crispy fried chicken with rice", "images/chickenjoy.png");
        insertMenuItem(pstmt, "Chickenjoy 2pc Solo", "Chicken", 195.00, "Two pieces crispy fried chicken", "images/chickenjoy.png");
        insertMenuItem(pstmt, "Chicken Inasal Paa", "Chicken", 132.00, "Grilled chicken leg quarter", "images/inasal.png");
        insertMenuItem(pstmt, "Chicken Inasal Pecho", "Chicken", 165.00, "Grilled chicken breast", "images/inasal.png");
        insertMenuItem(pstmt, "Spicy Chicken 1pc", "Chicken", 109.00, "Spicy crispy chicken", "images/spicy_chicken.png");
        
        // Burgers
        insertMenuItem(pstmt, "Yumburger", "Burgers", 50.00, "Classic beef burger", "images/yumburger.png");
        insertMenuItem(pstmt, "Cheeseburger", "Burgers", 62.00, "Beef burger with cheese", "images/cheeseburger.png");
        insertMenuItem(pstmt, "Champ Burger", "Burgers", 139.00, "Premium beef burger", "images/champ.png");
        insertMenuItem(pstmt, "Amazing Aloha Burger", "Burgers", 139.00, "Beef with pineapple and bacon", "images/aloha.png");
        insertMenuItem(pstmt, "Double Champ", "Burgers", 189.00, "Double patty burger", "images/double_champ.png");
        
        // Pasta
        insertMenuItem(pstmt, "Jolly Spaghetti", "Pasta", 65.00, "Sweet spaghetti with hotdog", "images/spaghetti.png");
        insertMenuItem(pstmt, "Jolly Spaghetti Family", "Pasta", 299.00, "Family size spaghetti", "images/spaghetti_family.png");
        insertMenuItem(pstmt, "Palabok Fiesta", "Pasta", 89.00, "Filipino palabok", "images/palabok.png");
        insertMenuItem(pstmt, "Carbonara", "Pasta", 129.00, "Creamy carbonara", "images/carbonara.png");
        
        // Rice Meals
        insertMenuItem(pstmt, "Menudo", "Rice Meals", 140.00, "Pork menudo", "images/menudo.png");
        insertMenuItem(pstmt, "Bicol Express", "Rice Meals", 140.00, "Spicy pork in coconut milk", "images/bicol_express.png");
        insertMenuItem(pstmt, "Pork Sinigang", "Rice Meals", 150.00, "Sour pork soup", "images/sinigang.png");
        insertMenuItem(pstmt, "Pork Caldereta", "Rice Meals", 160.00, "Pork stew", "images/caldereta.png");
        insertMenuItem(pstmt, "Adobo Flakes", "Rice Meals", 120.00, "Crispy adobo flakes", "images/adobo.png");
        insertMenuItem(pstmt, "Bangus Silog", "Rice Meals", 135.00, "Milkfish with rice and egg", "images/bangus.png");
        insertMenuItem(pstmt, "Tapsilog", "Rice Meals", 125.00, "Beef tapa with rice and egg", "images/tapsilog.png");
        insertMenuItem(pstmt, "Tosilog", "Rice Meals", 115.00, "Pork tocino with rice and egg", "images/tosilog.png");
        
        // Drinks
        insertMenuItem(pstmt, "Coke Regular", "Drinks", 35.00, "Coca-Cola 12oz", "images/coke.png");
        insertMenuItem(pstmt, "Coke Large", "Drinks", 55.00, "Coca-Cola 16oz", "images/coke.png");
        insertMenuItem(pstmt, "Pineapple Juice", "Drinks", 45.00, "Fresh pineapple juice", "images/pineapple.png");
        insertMenuItem(pstmt, "Iced Tea", "Drinks", 40.00, "Refreshing iced tea", "images/iced_tea.png");
        insertMenuItem(pstmt, "Coffee", "Drinks", 50.00, "Hot brewed coffee", "images/coffee.png");
        
        // Desserts
        insertMenuItem(pstmt, "Peach Mango Pie", "Desserts", 55.00, "Sweet peach mango pie", "images/pie.png");
        insertMenuItem(pstmt, "Turon", "Desserts", 35.00, "Banana spring roll", "images/turon.png");
        insertMenuItem(pstmt, "Halo-Halo", "Desserts", 89.00, "Shaved ice dessert", "images/halo_halo.png");
        insertMenuItem(pstmt, "Leche Flan", "Desserts", 65.00, "Caramel custard", "images/flan.png");
        
        // Sides
        insertMenuItem(pstmt, "French Fries Regular", "Sides", 59.00, "Crispy french fries", "images/fries.png");
        insertMenuItem(pstmt, "French Fries Large", "Sides", 89.00, "Large french fries", "images/fries.png");
        insertMenuItem(pstmt, "Coke Float", "Sides", 75.00, "Coke with ice cream", "images/coke_float.png");
        insertMenuItem(pstmt, "Extra Rice", "Sides", 25.00, "Additional rice", "images/rice.png");
        insertMenuItem(pstmt, "Gravy", "Sides", 20.00, "Extra gravy", "images/gravy.png");
        
        pstmt.close();
        System.out.println("Default menu items inserted!");
    }
    
    private void insertMenuItem(PreparedStatement pstmt, String name, String category, 
            double price, String description, String imagePath) throws SQLException {
        pstmt.setString(1, name);
        pstmt.setString(2, category);
        pstmt.setDouble(3, price);
        pstmt.setString(4, description);
        pstmt.setString(5, imagePath);
        pstmt.executeUpdate();
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
            // Also include items where available is NULL (for backwards compatibility)
            ResultSet rs = stmt.executeQuery("SELECT * FROM menu_items WHERE available = 1 OR available IS NULL ORDER BY category, name");
            System.out.println("Executing getAllMenuItems query...");
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
            System.out.println("getAllMenuItems returned " + items.size() + " items");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error fetching menu items: " + e.getMessage());
            e.printStackTrace();
        }
        return items;
    }
    
    public List<MenuItem> getMenuItemsByCategory(String category) {
        List<MenuItem> items = new ArrayList<>();
        try {
            if (connection == null || connection.isClosed()) {
                System.err.println("Database connection is closed!");
                return items;
            }
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
            PreparedStatement pstmt = connection.prepareStatement("UPDATE menu_items SET available = 0 WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
    
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
