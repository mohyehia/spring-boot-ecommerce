package com.mohyehia.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ORDER_ID")
    private long orderId;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "STATUS_CODE", referencedColumnName = "CODE")
    private Status status;

    @Column(name = "NOTES")
    private String notes;
}
