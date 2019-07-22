package com.webshop.internship.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderProducts")
public class Order implements Serializable {
    private static final long serialVersionUID = -8279544909966565472L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1L, message = "Invalid order ID.")
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private String status;

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.order")
    @Valid
    private List <OrderProduct> orderProducts = new ArrayList <>();

    public Order() {
        //this is a constructor, not a method, Sonar!
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

}
