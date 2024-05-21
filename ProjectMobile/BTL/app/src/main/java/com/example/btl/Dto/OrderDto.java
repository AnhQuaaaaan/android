package com.example.btl.Dto;

import java.io.Serializable;

public class OrderDto implements Serializable {
    private int id, quantity;
    private String img, name, color, size, ten, dienthoai, email, diachi,tongtien;
    private double price;

    public OrderDto() {
    }

    public OrderDto(int id, int quantity, String img, String name, String color, String size, String ten, String dienthoai, String email, String diachi, double price,String tongtien) {
        this.id = id;
        this.quantity = quantity;
        this.img = img;
        this.name = name;
        this.color = color;
        this.size = size;
        this.ten = ten;
        this.dienthoai = dienthoai;
        this.email = email;
        this.diachi = diachi;
        this.price = price;
        this.tongtien=tongtien;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
