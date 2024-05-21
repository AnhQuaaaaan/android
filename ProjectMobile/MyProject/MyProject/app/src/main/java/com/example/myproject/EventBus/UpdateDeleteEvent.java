package com.example.myproject.EventBus;

import com.example.myproject.model.Product;

public class UpdateDeleteEvent {
    private Product product;

    public UpdateDeleteEvent(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
