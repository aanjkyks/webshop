package com.webshop.internship.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ProductTest {
    private Product testProduct = new Product();

    @Before
    public void setUpTestProduct() {
        testProduct.setId(1L);
        testProduct.setName("name");
        testProduct.setPictureUrl("url");
        testProduct.setPrice(20.0);
    }

    @Test
    public void productTestGettersSetters() {
        assertEquals(java.util.Optional.of(1L), java.util.Optional.ofNullable(testProduct.getId()));
        assertEquals("name", testProduct.getName());
        assertEquals("url", testProduct.getPictureUrl());
        assertEquals(java.util.Optional.of(20.0), java.util.Optional.ofNullable(testProduct.getPrice()));
    }

    @Test
    public void productTestConstructorEquals() {
        Product anotherTestProduct = new Product(testProduct.getId(), testProduct.getName(), testProduct.getPrice(),
                testProduct.getPictureUrl());
        assertEquals(testProduct, anotherTestProduct);
        anotherTestProduct.setName("different name");
        assertNotEquals(testProduct, anotherTestProduct);
        anotherTestProduct.setId(67L);
        assertNotEquals(testProduct, anotherTestProduct);
        assertEquals(testProduct, testProduct);
        assertNotEquals(testProduct, null);
        assertNotEquals(testProduct, String.valueOf(8));
    }

    @Test
    public void productTestHashCode() {
        Product anotherTestProduct = new Product(testProduct.getId(), testProduct.getName(), testProduct.getPrice(),
                testProduct.getPictureUrl());
        assertEquals(testProduct.hashCode(), anotherTestProduct.hashCode());
    }

}