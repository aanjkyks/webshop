package com.webshop.internship.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ProductTest {
    @Test
    public void productTest() {
        Product testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("name");
        testProduct.setPictureUrl("url");
        testProduct.setPrice(20.0);

        assertEquals(java.util.Optional.of(1L), java.util.Optional.ofNullable(testProduct.getId()));
        assertEquals("name", testProduct.getName());
        assertEquals("url", testProduct.getPictureUrl());
        assertEquals(java.util.Optional.of(20.0), java.util.Optional.ofNullable(testProduct.getPrice()));

        Product anotherTestProduct = new Product(testProduct.getId(), testProduct.getName(), testProduct.getPrice(),
                testProduct.getPictureUrl());
        assertNotEquals(testProduct, anotherTestProduct);


    }

}