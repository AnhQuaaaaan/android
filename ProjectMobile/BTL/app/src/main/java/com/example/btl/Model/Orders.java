package com.example.btl.Model;

public class Orders {
    private int id,clothes_id;
    private String name,email,phoneNumber,quantity,address;
    private Double totalPrice;

    public Orders() {
    }

    public Orders(int id, int clothes_id, String name, String email, String phoneNumber, String quantity, String address, Double totalPrice) {
        this.id = id;
        this.clothes_id = clothes_id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.quantity = quantity;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClothes_id() {
        return clothes_id;
    }

    public void setClothes_id(int clothes_id) {
        this.clothes_id = clothes_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
