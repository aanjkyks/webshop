package com.webshop.internship.dto;

import com.webshop.internship.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class OrderProductDTOTest {
    @Test
    public void testOrderProductDTO() {
        OrderProductDTO dto = new OrderProductDTO();
        Product product = new Product(1L, "name", 50.0, "url");
        dto.setProduct(product);
        dto.setQuantity(5);
        Assert.assertEquals(product, dto.getProduct());
        Assert.assertEquals(Optional.of(5), Optional.ofNullable(dto.getQuantity()));
    }

}