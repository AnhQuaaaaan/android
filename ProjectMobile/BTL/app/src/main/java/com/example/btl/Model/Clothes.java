package com.example.btl.Model;

import java.io.Serializable;

public class Clothes implements Serializable {
    private int id;
    private String img,name,size,color;
    private double price;

    public Clothes() {
    }

    public Clothes(int id, String img, String name, String size, String color, double price) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
    }

    public Clothes(String img, String name, String size, String color, double price) {
        this.img = img;
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
