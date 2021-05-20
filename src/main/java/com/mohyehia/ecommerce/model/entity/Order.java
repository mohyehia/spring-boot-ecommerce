package com.mohyehia.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order")
public class Order {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "REFERENCE_NUMBER")
    private long referenceNumber;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "SHIPPING_ADDRESS")
    private String shippingAddress;

    @Column(name = "BILLING_ADDRESS")
    private String billingAddress;

    @Column(name = "PRODUCTS_PRICE")
    private BigDecimal productsPrice;

    @Column(name = "DELIVERY_COST")
    private BigDecimal deliveryCost;

    @Column(name = "DISCOUNT")
    private BigDecimal discount;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @Column(name = "SHIPPING_DATE")
    private LocalDateTime shippingDate;

    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;
}
