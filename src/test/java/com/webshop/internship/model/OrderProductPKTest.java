package com.webshop.internship.model;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class OrderProductPKTest {
    @Test
    public void testHashCode() {
        OrderProductPK pk = new OrderProductPK();
        pk.setProduct(null);
        pk.setOrder(null);
        assertEquals(0, pk.hashCode());

        Product product = new Product(1L, "name", 90.0, "url");
        pk.setProduct(product);
        Order order = new Order();
        OrderProduct orderProduct = new OrderProduct(order, product, 1);
        ArrayList <OrderProduct> orderProducts = new ArrayList <>();
        orderProducts.add(orderProduct);
        order.setOrderProducts(orderProducts);
        order.setDateCreated(LocalDate.now());
        order.setId(1L);
        order.setStatus("ok");
        pk.setOrder(order);
        assertNotEquals(0, pk.hashCode());

    }

    @Test
    public void testEquals() {
        OrderProductPK pk = new OrderProductPK();
        assertEquals(pk, pk);
        assertNotEquals(pk, null);
        assertNotEquals(pk, "String");
        OrderProductPK pk1 = new OrderProductPK();

        Product product = new Product(1L, "name", 90.0, "url");
        pk.setProduct(product);
        Order order = new Order();
        OrderProduct orderProduct = new OrderProduct(order, product, 1);
        ArrayList <OrderProduct> orderProducts = new ArrayList <>();
        orderProducts.add(orderProduct);
        order.setOrderProducts(orderProducts);
        order.setDateCreated(LocalDate.now());
        order.setId(1L);
        order.setStatus("ok");
        pk.setOrder(order);
        Product product1 = new Product(4L, "name", 90.0, "url");
        pk1.setProduct(product1);
        pk1.setOrder(order);

        assertNotEquals(pk, pk1);
        pk1.setProduct(product);
        assertEquals(pk, pk1);


    }

}