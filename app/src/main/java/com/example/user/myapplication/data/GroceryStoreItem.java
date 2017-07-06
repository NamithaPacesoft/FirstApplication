package com.example.user.myapplication.data;

/**
 * Created by User on 2017-06-16.
 */

public class GroceryStoreItem {

    private String productName;
    private float price;
    private int quantity;
    private String description;


   public GroceryStoreItem(String productName,float price, int quantity, String description){
       this.productName=productName;
       this.price=price;
       this.quantity=quantity;
       this.description=description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
