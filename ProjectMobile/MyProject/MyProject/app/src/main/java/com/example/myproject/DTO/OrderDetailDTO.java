package com.example.myproject.DTO;

public class OrderDetailDTO {
    private String image;
    private String name;
    private int quantity;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String image, String name, int quantity) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
