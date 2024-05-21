package com.example.myproject.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String image;
    private String price;
    private String description;
    private int categoryId;

    public Product() {
    }

    public Product(int id, String name, String image, String price, String description, int categoryId) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }
    public Product(String name, String image, String price, String description, int categoryId) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
