package com.webshop.internship;

import com.webshop.internship.model.Product;
import com.webshop.internship.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebShopApplication {

    @Autowired
    private ProductService productService;

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
            productService.save(new Product(4L, "Icecream", 5.00, url));
            productService.save(new Product(5L, "Beer", 3.00, url));
            productService.save(new Product(6L, "Phone", 500.00, url));
            productService.save(new Product(7L, "Watch", 30.00, url));
        };
    }
}
