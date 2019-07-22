package com.webshop.internship.model;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class OrderProductTest {
    @Test
    public void testGetTotalPrice() {
        OrderProduct orderProduct = new OrderProduct(new Order(), new Product(89L, "name", 89.0, "url"), 5);
        assertEquals(Optional.of(445.0), Optional.ofNullable(orderProduct.getTotalPrice()));
    }
}