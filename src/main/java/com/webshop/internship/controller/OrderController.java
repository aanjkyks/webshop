package com.webshop.internship.controller;

import com.webshop.internship.model.Order;
import com.webshop.internship.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "order/{orderId:[\\d]+}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional <Order> getOrder(@PathVariable Long orderId) {
        return orderService.findById(orderId);
    }
}
