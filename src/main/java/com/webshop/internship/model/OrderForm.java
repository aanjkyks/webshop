package com.webshop.internship.model;

import com.webshop.internship.dto.OrderProductDTO;

import java.util.List;

public class OrderForm {

    private List <OrderProductDTO> productOrders;

    public List <OrderProductDTO> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List <OrderProductDTO> productOrders) {
        this.productOrders = productOrders;
    }

    public void addProduct(OrderProductDTO productDTO) {
        productOrders.add(productDTO);
    }
}