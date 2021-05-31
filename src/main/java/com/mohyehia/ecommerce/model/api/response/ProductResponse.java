package com.mohyehia.ecommerce.model.api.response;

import com.mohyehia.ecommerce.model.dto.ProductDTO;
import com.mohyehia.ecommerce.model.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductResponse {
    private int count;
    private List<ProductDTO> products;

    public ProductResponse(List<ProductDTO> products) {
        this.products = products;
        this.count = products.size();
    }
}
