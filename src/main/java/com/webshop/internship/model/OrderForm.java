package com.webshop.internship.model;

import com.webshop.internship.dto.OrderProductDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class OrderForm implements Serializable {

    private static final long serialVersionUID = 1241159165071564200L;
    private List <OrderProductDTO> productOrders = new ArrayList <>();

    public List <OrderProductDTO> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List <OrderProductDTO> productOrders) {
        this.productOrders = productOrders;
    }

    public void addProduct(OrderProductDTO productDTO) {
        productOrders.add(productDTO);
    }

    public void removeAllProducts() {
        productOrders = new ArrayList <>();
    }
}