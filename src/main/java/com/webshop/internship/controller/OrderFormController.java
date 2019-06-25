package com.webshop.internship.controller;

import com.webshop.internship.dto.OrderProductDTO;
import com.webshop.internship.model.Order;
import com.webshop.internship.model.OrderForm;
import com.webshop.internship.service.OrderService;
import com.webshop.internship.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@Scope("request")
public class OrderFormController {
    @Autowired
    private OrderForm orderForm;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("productId") long id, @RequestParam("quantity") int quantity) {
        OrderProductDTO productDTO = new OrderProductDTO();
        productDTO.setProduct(productService.getProduct(id));
        productDTO.setQuantity(quantity);
//        System.out.println(productDTO==null);
        orderForm.addProduct(productDTO);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public ResponseEntity <OrderForm> getCart() {
        return new ResponseEntity <>(orderForm, HttpStatus.OK);
    }

    @GetMapping("/emptyCart")
    public String emptyCart() {
        orderForm.removeAllProducts();
        return "redirect:/cart";
    }

    @GetMapping("/buy")
    public ResponseEntity <Order> buy() {
        Order order = orderService.prepare(orderForm);
        orderForm.removeAllProducts();
        String uri = ServletUriComponentsBuilder
                             .fromCurrentServletMapping()
                             .path("api/orders/{id}")
                             .buildAndExpand(order.getId())
                             .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);
        return new ResponseEntity <>(order, headers, HttpStatus.OK);
    }
}
