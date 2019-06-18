package com.webshop.internship.model;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class OrderProductTest {
    @Test
    public void testGetTotalPrice() {
        OrderProduct orderProduct = new OrderProduct(new Order(), new Product(89L, "name", 89.0, "url"), 5);
        assertEquals(Optional.of(445.0), Optional.ofNullable(orderProduct.getTotalPrice()));
    }

    @Test
    public void testConstructor() {
        Order order = new Order();
        Product product = new Product();
        OrderProduct orderProduct = new OrderProduct(order, product, 5);
        assertEquals(product, orderProduct.getProduct());
        assertEquals(order, orderProduct.getPk().getOrder());

        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setQuantity(5);
        OrderProductPK pk = new OrderProductPK();
        pk.setProduct(product);
        pk.setOrder(order);
        orderProduct1.setPk(pk);
        assertEquals(orderProduct, orderProduct1);
    }

    @Test
    public void testEquals() {
        Order order = new Order();
        order.setId(5L);
        Product product = new Product();
        OrderProduct orderProduct = new OrderProduct(order, product, 5);
        assertEquals(orderProduct, orderProduct);
        assertNotEquals(orderProduct, null);
        assertNotEquals(orderProduct, "String");

        OrderProduct orderProduct1 = new OrderProduct(order, product, 8);
        assertNotEquals(orderProduct, orderProduct1);

        OrderProductPK pk = new OrderProductPK();
        pk.setOrder(new Order());
        pk.setProduct(product);
        orderProduct1.setPk(pk);
        assertNotEquals(orderProduct, orderProduct1);
    }

    @Test
    public void testHashCode() {
        Order order = new Order();
        Product product = new Product();
        product.setId(6L);
        product.setName("name");
        product.setPrice(60.0);
        OrderProduct orderProduct = new OrderProduct(null, null, null);
        OrderProductPK pk = null;
        orderProduct.setPk(pk);
        assertEquals(0, orderProduct.hashCode());
        orderProduct = new OrderProduct(order, product, 5);
        assertNotEquals(0, orderProduct.hashCode());

    }

}