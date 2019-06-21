package com.webshop.internship.service;

import com.webshop.internship.controller.OrderAPIController;
import com.webshop.internship.model.Order;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface OrderService {
    @NotNull
    Iterable <Order> getAllOrders();

    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    Order prepare(OrderAPIController.OrderForm form);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);

    Optional <Order> findById(Long id);
}
