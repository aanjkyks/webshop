package com.webshop.internship.controller;


import com.webshop.internship.dto.OrderProductDTO;
import com.webshop.internship.model.Order;
import com.webshop.internship.service.OrderProductService;
import com.webshop.internship.service.OrderService;
import com.webshop.internship.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderAPIController {

    private static ProductService productService;
    private static OrderService orderService;
    private static OrderProductService orderProductService;

    public OrderAPIController(ProductService productService, OrderService orderService,
                              OrderProductService orderProductService) {
        OrderAPIController.productService = productService;
        OrderAPIController.orderService = orderService;
        OrderAPIController.orderProductService = orderProductService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable <Order> list() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity <Order> create(@RequestBody OrderForm form) {

        Order order = orderService.prepare(form);
        String uri = ServletUriComponentsBuilder
                             .fromCurrentServletMapping()
                             .path("/orders/{id}")
                             .buildAndExpand(order.getId())
                             .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity <>(order, headers, HttpStatus.CREATED);
    }


    public static class OrderForm {

        private List <OrderProductDTO> productOrders;

        public List <OrderProductDTO> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List <OrderProductDTO> productOrders) {
            this.productOrders = productOrders;
        }
    }
}