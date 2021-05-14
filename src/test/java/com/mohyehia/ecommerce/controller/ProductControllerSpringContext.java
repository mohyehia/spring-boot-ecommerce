package com.mohyehia.ecommerce.controller;

import com.github.javafaker.Faker;
import com.mohyehia.ecommerce.exception.ResourceNotFoundException;
import com.mohyehia.ecommerce.model.entity.Product;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerSpringContext extends GlobalSpringContext {

    @BeforeAll
    static void initializeFaker() {
        faker = new Faker(Locale.ENGLISH);
    }

    @Test
    @DisplayName("test retrieving all products")
    void when_calling_retrieve_products_endpoint_then_return_all_products() throws Exception {
        List<Product> products = populateRandomProducts();
        BDDMockito.given(productService.findAll()).willReturn(products);
        mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.count", CoreMatchers.equalTo(3)));
    }

    @Test
    @DisplayName("test retrieving only one product by id")
    void when_calling_retrieve_product_by_id_endpoint_then_return_only_one_product() throws Exception {
        Product product = Product.builder()
                .id(1L)
                .name(faker.artist().name())
                .description(faker.book().title())
                .categoryId(1)
                .price(new BigDecimal(100))
                .imageUrl(faker.internet().url())
                .build();
        BDDMockito.given(productService.findById(ArgumentMatchers.anyLong())).willReturn(product);
        mockMvc.perform(get("/api/v1/products/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("test retrieving product by an invalid id then throw not found exception")
    void when_calling_retrieve_product_by_invalid_id_endpoint_then_throw_not_found_Exception() throws Exception {
        BDDMockito.given(productService.findById(ArgumentMatchers.anyLong())).willReturn(null);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/{id}", 123)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Exception resolvedException = mvcResult.getResolvedException();
        Assertions.assertThat(resolvedException)
                .isNotNull()
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(messageSource.getMessage("PRODUCT_NOT_FOUND", new Object[]{}, Locale.ENGLISH));

    }

    private List<Product> populateRandomProducts() {
        return Arrays.asList(
                Product.builder()
                        .id(1L)
                        .name(faker.artist().name())
                        .description(faker.book().title())
                        .categoryId(1)
                        .price(new BigDecimal(100))
                        .imageUrl(faker.internet().url())
                        .build(),
                Product.builder()
                        .id(2L)
                        .name(faker.artist().name())
                        .description(faker.book().title())
                        .categoryId(1)
                        .price(new BigDecimal(100))
                        .imageUrl(faker.internet().url())
                        .build(),
                Product.builder()
                        .id(3L)
                        .name(faker.artist().name())
                        .description(faker.book().title())
                        .categoryId(1)
                        .price(new BigDecimal(100))
                        .imageUrl(faker.internet().url())
                        .build()
        );
    }
}