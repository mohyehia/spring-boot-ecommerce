package com.mohyehia.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Role {
    @Id
    @Column(name = "CODE")
    private final String code;

    @Column(name = "NAME")
    private String name;
}
