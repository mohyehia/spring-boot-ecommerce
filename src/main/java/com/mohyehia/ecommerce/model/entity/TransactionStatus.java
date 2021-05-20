package com.mohyehia.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "transaction_status")
public class TransactionStatus {
    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;
}
