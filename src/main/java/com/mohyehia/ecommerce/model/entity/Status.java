package com.mohyehia.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;
}
