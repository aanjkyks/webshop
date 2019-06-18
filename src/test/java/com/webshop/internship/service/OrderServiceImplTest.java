package com.webshop.internship.service;

import com.webshop.internship.model.Order;
import com.webshop.internship.repository.OrderRepository;
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

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @Before
    public void setUp() {
        orderService = new OrderServiceImpl(orderRepository);
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
        LocalDate testDate = LocalDate.MAX;
        Assert.assertNotEquals(testDate, orderService.create(order).getDateCreated());
    }
}