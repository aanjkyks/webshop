package com.webshop.internship.service;

import com.webshop.internship.model.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface ProductService {

    @NotNull Iterable <Product> getAllProducts();

    Product getProduct(@Min(value = 1L, message = "Invalid product ID.") Long id);

    void save(Product product);

    void update(Long id, String name, Double price, String url);
}
