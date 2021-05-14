package com.mohyehia.ecommerce.model.api.response;

import com.mohyehia.ecommerce.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CategoryProductsResponse {
    private final Category category;
    private final ProductResponse productResponse;
}
