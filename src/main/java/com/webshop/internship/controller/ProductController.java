package com.webshop.internship.controller;


import com.webshop.internship.model.Product;
import com.webshop.internship.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"", "/"})
    public @NotNull Iterable <Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }
}