package com.mohyehia.ecommerce.service.impl;

import com.mohyehia.ecommerce.repository.CategoryRepository;
import com.mohyehia.ecommerce.model.entity.Category;
import com.mohyehia.ecommerce.service.framework.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
