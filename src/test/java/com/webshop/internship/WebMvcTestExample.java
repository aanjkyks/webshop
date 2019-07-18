package com.webshop.internship;

import com.webshop.internship.controller.ProductController;
import com.webshop.internship.model.Product;
import com.webshop.internship.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class WebMvcTestExample {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ProductService productService;

    @Test
    public void testProductController() throws Exception {
        String url = "http://placehold.it/200x100";
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "TV Set", 300.00, url));
        products.add(new Product(2L, "Game Console", 200.00, url));

        given(productService.getAllProducts()).willReturn(products);

        mvc.perform(get("/api/products/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(products.get(0).getName())));
    }


}
