package com.webshop.internship.model;

import org.junit.Test;

import java.time.LocalDate;
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

    @Test
    public void testOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus("OK");
        order.setDateCreated(LocalDate.now());
        List <OrderProduct> orderProducts = new ArrayList <>();
        OrderProduct orderProduct = new OrderProduct(new Order(), new Product(), 5);
        orderProducts.add(orderProduct);
        order.setOrderProducts(orderProducts);

        assertEquals(order.getDateCreated(), LocalDate.now());
        assertEquals(Optional.ofNullable(order.getId()), Optional.of(1L));
        assertEquals(order.getNumberOfProducts(), 1);
        assertEquals(order.getOrderProducts(), orderProducts);
        assertEquals(order.getStatus(), "OK");

    }

}