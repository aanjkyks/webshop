package com.webshop.internship.service;

import com.webshop.internship.exception.ResourceNotFoundException;
import com.webshop.internship.model.Product;
import com.webshop.internship.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable <Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}