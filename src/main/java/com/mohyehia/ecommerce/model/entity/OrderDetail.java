package com.mohyehia.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ORDER_ID")
    private long orderId;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private Product product;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE_PER_UNIT")
    private BigDecimal pricePerUnit;

    @Column(name = "TOTAL")
    private BigDecimal total;
}
