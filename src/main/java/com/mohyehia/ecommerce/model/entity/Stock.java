package com.mohyehia.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @Column(name = "PRODUCT_ID")
    private long productId;

    @Column(name = "IN_STOCK")
    private int inStock;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
