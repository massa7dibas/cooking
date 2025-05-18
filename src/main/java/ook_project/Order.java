package ook_project;

import java.util.*;

public class Order {
    private final String orderId;
    private final List<FoodItem> items;
    private final double totalPrice;
    private final String status;

    public Order(String orderId, List<FoodItem> items, double totalPrice, String status) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
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

}
