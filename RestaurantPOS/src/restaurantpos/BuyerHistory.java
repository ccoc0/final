package restaurantpos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * BuyerHistory - Model class for purchase records
 * @author RestaurantPOS
 */
public class BuyerHistory {
    private int id;
    private String buyerName;
    private String itemsOrdered;
    private double totalAmount;
    private String orderDate;
    private String paymentMethod;
    private String status;
    
    public BuyerHistory() {
        this.orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = "Completed";
    }
    
    public BuyerHistory(String buyerName, String itemsOrdered, double totalAmount, String paymentMethod) {
        this.buyerName = buyerName;
        this.itemsOrdered = itemsOrdered;
        this.totalAmount = totalAmount;
        this.orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.paymentMethod = paymentMethod;
        this.status = "Completed";
    }
    
    public BuyerHistory(int id, String buyerName, String itemsOrdered, double totalAmount, String orderDate, String paymentMethod, String status) {
        this.id = id;
        this.buyerName = buyerName;
        this.itemsOrdered = itemsOrdered;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }
    
    // Getters
    public int getId() { return id; }
    public String getBuyerName() { return buyerName; }
    public String getItemsOrdered() { return itemsOrdered; }
    public double getTotalAmount() { return totalAmount; }
    public String getOrderDate() { return orderDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getStatus() { return status; }
    
    // Setters
    public void setId(int id) { this.id = id; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }
    public void setItemsOrdered(String itemsOrdered) { this.itemsOrdered = itemsOrdered; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setStatus(String status) { this.status = status; }
    
    public String getFormattedTotal() {
        return String.format("P%.2f", totalAmount);
    }
    
    @Override
    public String toString() {
        return buyerName + " - " + getFormattedTotal() + " (" + orderDate + ")";
    }
}
