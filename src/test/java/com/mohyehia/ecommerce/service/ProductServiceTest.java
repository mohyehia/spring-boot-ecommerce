package com.mohyehia.ecommerce.service;

import com.github.javafaker.Faker;
import com.mohyehia.ecommerce.model.entity.Product;
import com.mohyehia.ecommerce.service.framework.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductService productService;

    private static Faker faker;

    @BeforeAll
    static void initializeFaker() {
        faker = new Faker(Locale.ENGLISH);
    }

    @Test
    @DisplayName("test retrieving all products")
    void when_calling_findAll_method_then_return_all_products() {
        Product product1 = populateRandomProduct(1L);
        Product product2 = populateRandomProduct(2L);
        Product product3 = populateRandomProduct(3L);
        List<Product> products = Arrays.asList(product1, product2, product3);
        BDDMockito.given(productService.findAll()).willReturn(products);
        Assertions.assertThat(productService.findAll())
                .hasSize(3)
                .contains(product1, product2, product3)
                .doesNotContainNull();
    }

    @Test
    @DisplayName("test retrieving one product by id")
    void when_calling_findById_method_then_return_only_one_product() {
        Product product = populateRandomProduct(1L);
        BDDMockito.given(productService.findById(ArgumentMatchers.anyLong())).willReturn(product);
        Product retrievedProduct = productService.findById(1L);
        Assertions.assertThat(retrievedProduct).isNotNull();
        Assertions.assertThat(retrievedProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    @DisplayName("test retrieving a product with not found id then return null")
    void when_calling_findById_method_with_not_found_id_then_return_null() {
        BDDMockito.given(productService.findById(ArgumentMatchers.anyLong())).willReturn(null);
        Product retrievedProduct = productService.findById(1);
        Assertions.assertThat(retrievedProduct).isNull();
    }

    @Test
    @DisplayName("test retrieving list of products by categoryId")
    void when_calling_findByCategoryId_method_then_return_list_of_products() {
        Product product1 = populateRandomProduct(1L);
        Product product2 = populateRandomProduct(2L);
        List<Product> products = Arrays.asList(product1, product2);
        BDDMockito.given(productService.findByCategoryId(ArgumentMatchers.anyInt())).willReturn(products);
        Assertions.assertThat(productService.findByCategoryId(1))
                .hasSize(2)
                .contains(product1, product2)
                .doesNotContainNull();
    }

    private Product populateRandomProduct(long id) {
        return Product.builder()
                .id(id)
                .name(faker.artist().name())
                .description(faker.book().title())
                .categoryId(1)
                .price(new BigDecimal(100))
                .imageUrl(faker.internet().url())
                .build();
    }
}