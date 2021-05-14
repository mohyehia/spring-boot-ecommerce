package com.mohyehia.ecommerce.repository;

import com.mohyehia.ecommerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(int categoryId);
}
