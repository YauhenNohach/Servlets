package org.example.model;

public class PurchaseItem {
    private int id;
    private int purchaseOrderId;
    private int orderId;
    private double price;
    private int quantity;

    public PurchaseItem() {
    }

    public PurchaseItem(int purchaseOrderId, int orderId, double price, int quantity) {
        this.purchaseOrderId = purchaseOrderId;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(int purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
