package com.webshop.internship;

import com.webshop.internship.controller.OrderAPIController;
import com.webshop.internship.dto.OrderProductDTO;
import com.webshop.internship.model.Order;
import com.webshop.internship.model.Product;
import com.webshop.internship.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebShopApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebShopApplicationIntegrationTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    @Test
    public void testGetProducts() {
        ResponseEntity <Iterable <Product>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api" +
                                                                                           "/products",
                HttpMethod.GET, null, new ParameterizedTypeReference <Iterable <Product>>() {
                });
        Iterable <Product> products = responseEntity.getBody();
        Assertions.assertThat(products).hasSize(7);

        assertThat(products, hasItem(hasProperty("name", is("TV Set"))));
        assertThat(products, hasItem(hasProperty("name", is("Game Console"))));
        assertThat(products, hasItem(hasProperty("name", is("Sofa"))));
        assertThat(products, hasItem(hasProperty("name", is("Ice Cream"))));
        assertThat(products, hasItem(hasProperty("name", is("Beer"))));
        assertThat(products, hasItem(hasProperty("name", is("Phone"))));
        assertThat(products, hasItem(hasProperty("name", is("Watch"))));
    }

    @Test
    public void testGetNoOrdersCreated() {
        ResponseEntity <Iterable <Order>> responseEntity =
                restTemplate.exchange("http://localhost:" + port + "/api" + "/orders", HttpMethod.GET,
                        null, new ParameterizedTypeReference <Iterable <Order>>() {
                        });

        Iterable <Order> orders = responseEntity.getBody();
        Assertions.assertThat(orders).hasSize(1);
    }

    @Test
    public void testOrderCreation() {
        final ResponseEntity <Order> postResponse =
                restTemplate.postForEntity("http://localhost:" + port + "/api" +
                                                   "/orders", prepareOrderForm(), Order.class);
        Order order = postResponse.getBody();
        Assert.assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

        assertThat(order, hasProperty("status", is("PAID")));
        assertThat(order.getOrderProducts(), hasItem(hasProperty("quantity", is(2))));
//        Assert.assertEquals(1, order.getOrderProducts().size());
//        Assert.assertEquals(Optional.ofNullable(order.getOrderProducts().get(0).getQuantity()), Optional.of(2));
    }

    private OrderAPIController.OrderForm prepareOrderForm() {
        OrderAPIController.OrderForm orderForm = new OrderAPIController.OrderForm();
        OrderProductDTO productDto = new OrderProductDTO();
        productDto.setProduct(new Product(1L, "TV Set", 300.00, "http://placehold.it/200x100"));
        productDto.setQuantity(2);
        orderForm.setProductOrders(Collections.singletonList(productDto));

        return orderForm;
    }

}