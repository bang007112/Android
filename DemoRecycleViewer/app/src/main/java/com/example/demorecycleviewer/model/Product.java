package com.example.demorecycleviewer.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private String manufacture;

    public Product(int productId, String productName, String manufacture) {
        this.productId = productId;
        this.productName = productName;
        this.manufacture = manufacture;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }
}
