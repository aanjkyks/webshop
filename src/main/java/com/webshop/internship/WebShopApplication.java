package com.webshop.internship;

import com.webshop.internship.controller.OrderAPIController;
import com.webshop.internship.dto.OrderProductDTO;
import com.webshop.internship.model.Product;
import com.webshop.internship.service.OrderService;
import com.webshop.internship.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class WebShopApplication {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderAPIController orderAPIController;

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductService productService) {
        return args -> {
            String url = "http://placehold.it/200x100";
            productService.save(new Product(1L, "TV Set", 300.00, url));
            productService.save(new Product(2L, "Game Console", 200.00, url));
            productService.save(new Product(3L, "Sofa", 100.00, url));
            productService.save(new Product(4L, "Ice Cream", 5.00, url));
            productService.save(new Product(5L, "Beer", 3.00, url));
            productService.save(new Product(6L, "Phone", 500.00, url));
            productService.save(new Product(7L, "Watch", 30.00, url));

            orderService.prepare(prepareOrderForm());

        };
    }

    private OrderAPIController.OrderForm prepareOrderForm() {
        OrderAPIController.OrderForm orderForm = new OrderAPIController.OrderForm();
        OrderProductDTO productDto = new OrderProductDTO();
        productDto.setProduct(new Product(2L, "Game Console", 200.00, "http://placehold.it/200x100"));
        productDto.setQuantity(2);
        orderForm.setProductOrders(Collections.singletonList(productDto));

        return orderForm;
    }
}
