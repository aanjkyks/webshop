package com.webshop.internship.service;

import com.webshop.internship.dto.OrderProductDTO;
import com.webshop.internship.exception.ResourceNotFoundException;
import com.webshop.internship.model.Order;
import com.webshop.internship.model.OrderForm;
import com.webshop.internship.model.OrderProduct;
import com.webshop.internship.model.OrderStatus;
import com.webshop.internship.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private ProductService productService;


    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public @NotNull Iterable <Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order create(@NotNull(message = "The order cannot be null.") @Valid Order order) {
        order.setDateCreated(LocalDate.now());
        return orderRepository.save(order);
    }

    @Override
    public Order prepare(OrderForm form) {
        List <OrderProductDTO> formDTOs = form.getProductOrders();
        validateProductsExistence(formDTOs);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = orderService.create(order);

        List <OrderProduct> orderProducts = new ArrayList <>();
        for (OrderProductDTO dto : formDTOs) {
            orderProducts.add(orderProductService.create(new OrderProduct(order,
                    productService.getProduct(dto.getProduct().getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);
        orderService.update(order);
        return order;
    }

    @Override
    public void update(@NotNull(message = "The order cannot be null.") @Valid Order order) {
        orderRepository.save(order);
    }

    @Override
    public Optional <Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    private void validateProductsExistence(List <OrderProductDTO> orderProducts) {
        List <OrderProductDTO> list = new ArrayList <>();
        for (OrderProductDTO op : orderProducts) {
            if (!Objects.isNull(productService.getProduct(op.getProduct().getId()))) {
                list.add(op);
            }
        }

        if (CollectionUtils.isEmpty(list)) {
            throw new ResourceNotFoundException("Product not found");
        }
    }
}
