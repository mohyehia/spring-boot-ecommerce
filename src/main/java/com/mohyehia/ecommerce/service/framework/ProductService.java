package com.mohyehia.ecommerce.service.framework;

import com.mohyehia.ecommerce.model.dto.ProductDTO;
import com.mohyehia.ecommerce.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(long id);

    List<Product> findByCategoryId(int categoryId);

    List<ProductDTO> retrieveAllProducts();

    List<ProductDTO> findProductsByCategoryId(int categoryId);

    ProductDTO findProductById(long id);
}
