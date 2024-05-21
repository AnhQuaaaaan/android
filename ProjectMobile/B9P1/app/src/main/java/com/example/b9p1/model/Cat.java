package com.example.b9p1.model;

public class Cat {
    private int img;
    private String name;
    private double price;
    private String infor;

    public Cat(int img, String name, double price, String infor) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.infor = infor;
    }

    public Cat() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }
}
