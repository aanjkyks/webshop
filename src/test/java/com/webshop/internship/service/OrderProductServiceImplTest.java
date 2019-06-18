package com.webshop.internship.service;

import com.webshop.internship.model.Order;
import com.webshop.internship.model.OrderProduct;
import com.webshop.internship.model.Product;
import com.webshop.internship.repository.OrderProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class OrderProductServiceImplTest {
    @Autowired
    private OrderProductService orderProductService;
    @Mock
    private OrderProductRepository orderProductRepository;

    @Before
    public void setUp() {
        orderProductService = new OrderProductServiceImpl(orderProductRepository);
    }

    @Test
    public void testCreate() {
        Mockito.when(orderProductRepository.save(ArgumentMatchers.any(OrderProduct.class))).thenAnswer(invocation -> invocation.getArguments()[0]);
        OrderProduct orderProduct = new OrderProduct(new Order(), new Product(), 1);
        Assert.assertEquals(orderProduct, orderProductService.create(orderProduct));
    }
}