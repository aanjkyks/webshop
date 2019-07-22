package com.webshop.internship.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = -8685228676132904478L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1L, message = "Invalid product ID.")
    private Long id;

    @NotNull(message = "Product name is required.")
    @Basic(optional = false)
    private String name;

    private Double price;

    private String pictureUrl;

    public Product() {
    }

    public Product(@Min(value = 1L, message = "Invalid product ID.") Long id,
                   @NotNull(message = "Product name is required.") String name, Double price, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pictureUrl = pictureUrl;
    }

}
