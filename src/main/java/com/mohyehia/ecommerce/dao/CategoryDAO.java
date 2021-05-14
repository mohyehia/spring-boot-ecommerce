package com.mohyehia.ecommerce.dao;

import com.mohyehia.ecommerce.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
}
