package com.webshop.internship.dto;

import com.webshop.internship.model.Product;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderProductDTO implements Serializable {

    private static final long serialVersionUID = -2500065846674536251L;
    private Product product;
    private Integer quantity;

}
