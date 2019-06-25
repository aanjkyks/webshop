package com.webshop.internship.controller;


import com.webshop.internship.model.Order;
import com.webshop.internship.model.OrderForm;
import com.webshop.internship.service.OrderProductService;
import com.webshop.internship.service.OrderService;
import com.webshop.internship.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderAPIController {

    private ProductService productService;
    private OrderService orderService;
    private OrderProductService orderProductService;

    public OrderAPIController(ProductService productService, OrderService orderService,
                              OrderProductService orderProductService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }

    @GetMapping(value = "{orderId:[\\d]+}")
    @ResponseStatus(HttpStatus.OK)
    public Optional <Order> getOrder(@PathVariable Long orderId) {
        return orderService.findById(orderId);
    }


    @GetMapping(value = {"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable <Order> list() {
        return orderService.getAllOrders();
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity <Order> create(@RequestBody OrderForm form) {

        Order order = orderService.prepare(form);
        String uri = ServletUriComponentsBuilder
                             .fromCurrentServletMapping()
                             .path("api/orders/{id}")
                             .buildAndExpand(order.getId())
                             .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity <>(order, headers, HttpStatus.CREATED);
    }
}