package com.mohyehia.ecommerce.service.framework;

import com.mohyehia.ecommerce.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(int id);
}
