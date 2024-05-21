package com.example.btl.Model;

import java.io.Serializable;

public class Cart implements Serializable {
    private int id,quantity;
    private String name,size,color;
    private String image;
    private double price;

    public Cart() {
    }

    public Cart(int id, int quantity, String name, String size, String color, String image, double price) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.size = size;
        this.color = color;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
