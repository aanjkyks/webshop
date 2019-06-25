package com.webshop.internship.dto;

import com.webshop.internship.model.Product;

import java.io.Serializable;

public class OrderProductDTO implements Serializable {

    private static final long serialVersionUID = -2500065846674536251L;
    private Product product;
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
