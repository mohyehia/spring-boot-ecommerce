package com.mohyehia.ecommerce.entity.api.response;

import com.mohyehia.ecommerce.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private int count;
    private List<Category> categories;
}
