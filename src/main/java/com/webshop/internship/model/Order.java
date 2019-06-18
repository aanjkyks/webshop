package com.webshop.internship.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = -8279544909966565472L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private String status;

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.order")
    @Valid
    private List <OrderProduct> orderProducts = new ArrayList <>();

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.orderProducts.size();
    }

    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0.0;
        List <OrderProduct> orderProductList = getOrderProducts();
        for (OrderProduct op : orderProductList) {
            sum += op.getTotalPrice();
        }
        return sum;
    }

    public List <OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List <OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

}
