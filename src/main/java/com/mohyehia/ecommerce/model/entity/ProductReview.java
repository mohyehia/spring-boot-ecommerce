package com.mohyehia.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product_reviews")
public class ProductReview {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "PRODUCT_ID")
    private long productId;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "REVIEW")
    private String review;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
