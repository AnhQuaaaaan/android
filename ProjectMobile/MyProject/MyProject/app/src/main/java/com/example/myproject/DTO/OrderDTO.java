package com.example.myproject.DTO;

import java.util.List;

public class OrderDTO {
    private int orderId;

    private List<OrderDetailDTO> listOrderDetailDTO;

    public OrderDTO() {}

    public OrderDTO(int orderId, List<OrderDetailDTO> listOrderDetailDTO) {
        this.orderId = orderId;
        this.listOrderDetailDTO = listOrderDetailDTO;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderDetailDTO> getListOrderDetailDTO() {
        return listOrderDetailDTO;
    }

    public void setListOrderDetailDTO(List<OrderDetailDTO> listOrderDetailDTO) {
        this.listOrderDetailDTO = listOrderDetailDTO;
    }
}
