package com.webshop.internship.repository;

import com.webshop.internship.model.OrderProduct;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository <OrderProduct, Long> {
}
