package com.mohyehia.ecommerce.controller;

import com.mohyehia.ecommerce.entity.Category;
import com.mohyehia.ecommerce.entity.api.response.CategoryResponse;
import com.mohyehia.ecommerce.exception.ResourceNotFoundException;
import com.mohyehia.ecommerce.service.framework.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final MessageSource messageSource;

    @GetMapping
    public ResponseEntity<CategoryResponse> findAllCategories() {
        List<Category> categories = categoryService.findAll();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCount(categories.size());
        categoryResponse.setCategories(categories);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") int id, Locale locale) {
        Category category = categoryService.findById(id);
        if (category == null) {
            throw new ResourceNotFoundException(messageSource.getMessage("CATEGORY_NOT_FOUND", new Object[]{}, locale));
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
