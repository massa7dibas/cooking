package cook_Project;

import java.util.*;

public class Order {
    private String orderId;
    private Date orderDate;
    private List<FoodItem> items;
    private double totalPrice;
    private String status;

    public Order(String orderId, Date orderDate, List<FoodItem> items, double totalPrice, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
