package com.mohyehia.ecommerce.controller;

import com.mohyehia.ecommerce.exception.ResourceNotFoundException;
import com.mohyehia.ecommerce.model.api.response.ProductResponse;
import com.mohyehia.ecommerce.model.entity.Product;
import com.mohyehia.ecommerce.service.framework.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@RequestMapping("/api/v1/products")
@Log4j2
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MessageSource messageSource;

    @GetMapping
    public ResponseEntity<ProductResponse> retrieveAll() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(new ProductResponse(products), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> retrieveId(@PathVariable("id") long id, Locale locale) {
        Product product = productService.findById(id);
        if (product == null) {
            throw new ResourceNotFoundException(messageSource.getMessage("PRODUCT_NOT_FOUND", new Object[]{}, locale));
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
