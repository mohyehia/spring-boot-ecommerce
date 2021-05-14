package com.mohyehia.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CATEGORY_ID")
    private int categoryId;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "IMAGE_URL")
    private String imageUrl;
}
