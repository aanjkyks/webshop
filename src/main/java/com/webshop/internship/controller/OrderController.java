package com.webshop.internship.controller;

import com.webshop.internship.model.Order;
import com.webshop.internship.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "order/{orderId:[\\d]+}")
    @ResponseStatus(HttpStatus.OK)
    public Optional <Order> getOrder(@PathVariable Long orderId) {
        return this.orderService.findById(orderId);
    }
}
