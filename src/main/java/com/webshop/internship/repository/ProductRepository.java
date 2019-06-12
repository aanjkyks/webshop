package com.webshop.internship.repository;

import com.webshop.internship.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {

}
