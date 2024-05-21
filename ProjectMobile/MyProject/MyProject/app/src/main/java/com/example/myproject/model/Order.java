package com.example.myproject.model;

public class Order {
    private int id;
    private String totalMoney;
    private int totalQuantity;
    private String email;
    private String phoneNumber;
    private String address;
    private User user;

    public Order() {
    }

    public Order(int id, String totalMoney, int totalQuantity, String email, String phoneNumber, String address, User user) {
        this.id = id;
        this.totalMoney = totalMoney;
        this.totalQuantity = totalQuantity;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.user = user;
    }

    public Order(String totalMoney, int totalQuantity, String email, String phoneNumber, String address, User user) {
        this.totalMoney = totalMoney;
        this.totalQuantity = totalQuantity;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
