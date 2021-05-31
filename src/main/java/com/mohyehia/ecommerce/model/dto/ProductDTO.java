package com.mohyehia.ecommerce.model.dto;

import java.math.BigDecimal;

public interface ProductDTO {
    long getProductId();

    String getProductName();

    String getProductDescription();

    BigDecimal getProductPrice();

    String getCategoryCode();

    String getCategoryName();

    String getImageUrl();

    int getCountInStock();

    double getRating();

    int getNumberOfReviews();
}
