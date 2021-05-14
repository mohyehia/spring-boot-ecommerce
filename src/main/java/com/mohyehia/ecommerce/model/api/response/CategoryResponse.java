package com.mohyehia.ecommerce.model.api.response;

import com.mohyehia.ecommerce.model.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private int count;
    private List<Category> categories;
}
