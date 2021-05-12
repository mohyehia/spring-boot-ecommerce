package com.mohyehia.ecommerce.service.impl;

import com.mohyehia.ecommerce.dao.CategoryDAO;
import com.mohyehia.ecommerce.entity.Category;
import com.mohyehia.ecommerce.service.framework.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDAO.findById(id).orElse(null);
    }
}
