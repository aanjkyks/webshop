package com.webshop.internship;

import com.webshop.internship.controller.OrderController;
import com.webshop.internship.controller.OrderFormController;
import com.webshop.internship.controller.ProductController;
import org.junit.Assert;
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
    @Autowired
    private OrderController orderController;
    @Autowired
    private OrderFormController orderFormController;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(productController);
        Assert.assertNotNull(orderController);
        Assert.assertNotNull(orderFormController);
    }

}
