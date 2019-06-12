package com.webshop.internship;

import com.webshop.internship.controller.ProductController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebShopApplicationTests {
    @Autowired
    private ProductController productController;

    @Test
    public void contextLoads() {
        Assertions
                .assertThat(productController)
                .isNotNull();
    }

}
