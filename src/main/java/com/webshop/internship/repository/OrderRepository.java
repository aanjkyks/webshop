package com.webshop.internship.repository;

import com.webshop.internship.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository <Order, Long> {

}
