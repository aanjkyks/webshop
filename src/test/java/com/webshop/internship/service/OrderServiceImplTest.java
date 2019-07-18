package com.webshop.internship.service;

import com.webshop.internship.model.Order;
import com.webshop.internship.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private ProductService productService;

    @Before
    public void setUp() {
        orderService = new OrderServiceImpl(orderRepository, orderProductService, productService);
    }

    @Test
    public void testCreate() {
        Order order = new Order();
        Mockito.when(orderRepository.save(ArgumentMatchers.any(Order.class))).thenAnswer(invocation -> invocation.getArguments()[0]);
        LocalDate testDate = LocalDate.now();
        Assert.assertEquals(testDate, orderService.create(order).getDateCreated());
    }

    @Test
    public void testUpdate() {
        Order order = new Order();
        Mockito.when(orderRepository.save(ArgumentMatchers.any(Order.class))).thenAnswer(invocation -> invocation.getArguments()[0]);
        Order order1 = new Order();
        order1.setDateCreated(LocalDate.MAX);
        orderService.update(order1);
        Assert.assertNotEquals(order, order1);
    }

    @Test
    public void testGetAllOrders() {
        Mockito.when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThat(orderService.getAllOrders()).hasSize(0);
    }
}
