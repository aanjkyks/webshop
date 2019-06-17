package com.webshop.internship.service;

import com.webshop.internship.exception.ResourceNotFoundException;
import com.webshop.internship.model.Product;
import com.webshop.internship.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testGetProductCase1() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(new Product()));
        try {
            productService.getProduct(anyLong());
        } catch (ResourceNotFoundException rnfe) {
            fail();
        }

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetProductCase2() {
        when(productRepository.findById(anyLong())).thenThrow(new ResourceNotFoundException());
        productService.getProduct(anyLong());
    }
}