package com.mohyehia.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ORDER_ID")
    private long orderId;

    @Column(name = "REFERENCE_NUMBER")
    private long referenceNumber;

    @OneToOne
    @JoinColumn(name = "PAYMENT_TYPE_CODE", referencedColumnName = "CODE")
    private PaymentType paymentType;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DATE")
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "TRANSACTION_STATUS", referencedColumnName = "CODE")
    private TransactionStatus transactionStatus;
}
