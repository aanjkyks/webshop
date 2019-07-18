package com.webshop.internship.bootstrap;

import com.webshop.internship.dto.OrderProductDTO;
import com.webshop.internship.model.OrderForm;
import com.webshop.internship.model.Product;
import com.webshop.internship.service.OrderService;
import com.webshop.internship.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {
    private ProductService productService;
    private OrderService orderService;

    public DataInitializer(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) {

        String url = "http://placehold.it/200x100";
        productService.save(new Product(1L, "TV Set", 300.00, url));
        productService.save(new Product(2L, "Game Console", 200.00, url));
        productService.save(new Product(3L, "Sofa", 100.00, url));
        productService.save(new Product(4L, "Ice Cream", 5.00, url));
        productService.save(new Product(5L, "Beer", 3.00, url));
        productService.save(new Product(6L, "Phone", 500.00, url));
        productService.save(new Product(7L, "Watch", 30.00, url));

        orderService.prepare(prepareOrderForm());
    }

    private OrderForm prepareOrderForm() {
        OrderForm orderForm = new OrderForm();
        OrderProductDTO productDto = new OrderProductDTO();
        productDto.setProduct(new Product(2L, "Game Console", 200.00, "http://placehold.it/200x100"));
        productDto.setQuantity(2);
        orderForm.setProductOrders(Collections.singletonList(productDto));

        return orderForm;
    }
}
