package com.webshop.internship;

import com.webshop.internship.controller.ProductController;
import com.webshop.internship.model.Product;
import com.webshop.internship.repository.ProductRepository;
import com.webshop.internship.service.ProductService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebShopApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebShopApplicationTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSampleProducts() {
        productService.save(new Product(1L, "TV Set", 300.00, "http://placehold.it/200x100"));
        productService.save(new Product(2L, "Game Console", 200.00, "http://placehold.it/200x100"));
        productService.save(new Product(3L, "Sofa", 100.00, "http://placehold.it/200x100"));
        productService.save(new Product(4L, "Ice Cream", 5.00, "http://placehold.it/200x100"));
        productService.save(new Product(5L, "Beer", 3.00, "http://placehold.it/200x100"));
        productService.save(new Product(6L, "Phone", 500.00, "http://placehold.it/200x100"));
        productService.save(new Product(7L, "Watch", 30.00, "http://placehold.it/200x100"));
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

        Assert.assertEquals("TV Set", productService.getProduct(1L).getName());
        productRepository.deleteAll();
        Assert.assertTrue(productRepository.findAll().isEmpty());
    }

}