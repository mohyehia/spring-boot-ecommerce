package com.mohyehia.ecommerce.service.impl;

import com.mohyehia.ecommerce.model.entity.Product;
import com.mohyehia.ecommerce.repository.ProductRepository;
import com.mohyehia.ecommerce.service.framework.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
