package com.mohyehia.ecommerce.service.framework;

import com.mohyehia.ecommerce.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(long id);

    List<Product> findByCategoryId(int categoryId);
}
