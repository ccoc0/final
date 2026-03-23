package restaurantpos;

/**
 * MenuItem - Model class for menu items
 * @author RestaurantPOS
 */
public class MenuItem {
    private int id;
    private String name;
    private String category;
    private double price;
    private String description;
    private String imagePath;
    private int quantity;
    
    public MenuItem() {
        this.quantity = 1;
    }
    
    public MenuItem(int id, String name, String category, double price, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
        this.quantity = 1;
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImagePath() { return imagePath; }
    public int getQuantity() { return quantity; }
    
    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public String getFormattedPrice() {
        return String.format("P%.2f", price);
    }
    
    public double getSubtotal() {
        return price * quantity;
    }
    
    public String getFormattedSubtotal() {
        return String.format("P%.2f", getSubtotal());
    }
    
    @Override
    public String toString() {
        return name + " - " + getFormattedPrice();
    }
}
