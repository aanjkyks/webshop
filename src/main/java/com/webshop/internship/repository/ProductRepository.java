package com.webshop.internship.repository;

import com.webshop.internship.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository <Product, Long> {

}
