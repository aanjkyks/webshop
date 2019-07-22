package com.webshop.internship.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    @Test
    public void testGetTotalOrderPrice() {
        List <OrderProduct> orderProducts = new ArrayList <>();
        Order order = new Order();
        order.setId(999L);
        Product product = new Product(1L, "name", 90.0, "url");
        Product product1 = new Product(2L, "another name", 76.0, "url");
        orderProducts.add(new OrderProduct(order, product, 2));
        orderProducts.add(new OrderProduct(order, product1, 9));
        order.setOrderProducts(orderProducts);
        assertEquals(Optional.of(864.0), Optional.of(order.getTotalOrderPrice()));
    }
}