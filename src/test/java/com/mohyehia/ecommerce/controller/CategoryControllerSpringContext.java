package com.mohyehia.ecommerce.controller;

import com.mohyehia.ecommerce.exception.ResourceNotFoundException;
import com.mohyehia.ecommerce.model.entity.Category;
import com.mohyehia.ecommerce.model.entity.Product;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
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
class CategoryControllerSpringContext extends GlobalSpringContext {
    @Test
    @DisplayName("test retrieving all categories")
    void when_calling_retrieve_categories_endpoint_then_return_all_categories() throws Exception {
        List<Category> categories = populateRandomCategories();
        BDDMockito.given(categoryService.findAll()).willReturn(categories);
        mockMvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.count", CoreMatchers.equalTo(3)));
    }

    @Test
    @DisplayName("test retrieving only one category by id")
    void when_calling_retrieve_category_by_id_endpoint_then_return_only_one_category() throws Exception {
        Category category = new Category(1, faker.code().toString(), faker.name().name());
        BDDMockito.given(categoryService.findById(ArgumentMatchers.anyInt())).willReturn(category);
        mockMvc.perform(get("/api/v1/categories/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("test retrieving category by an invalid id then throw not found exception")
    void when_calling_retrieve_category_by_invalid_id_endpoint_then_throw_not_found_Exception() throws Exception {
        BDDMockito.given(categoryService.findById(ArgumentMatchers.anyInt())).willReturn(null);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/categories/{id}", 123)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Exception resolvedException = mvcResult.getResolvedException();
        Assertions.assertThat(resolvedException)
                .isNotNull()
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(messageSource.getMessage("CATEGORY_NOT_FOUND", new Object[]{}, Locale.ENGLISH));
    }

    @Test
    @DisplayName("test retrieving products that belongs to a category id")
    void when_calling_retrieve_products_by_category_id_then_return_list_of_products() throws Exception {
        Category category = new Category(1, faker.code().toString(), faker.name().name());
        List<Product> products = populateRandomProducts();
        BDDMockito.given(productService.findByCategoryId(ArgumentMatchers.anyInt())).willReturn(products);
        BDDMockito.given(categoryService.findById(ArgumentMatchers.anyInt())).willReturn(category);
        mockMvc.perform(get("/api/v1/categories/{id}/products", 1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("test retrieving list of products by an invalid categoryId then throw not found exception")
    void when_calling_retrieve_products_by_categoryId_with_invalid_id_endpoint_then_throw_not_found_Exception() throws Exception {
        BDDMockito.given(categoryService.findById(ArgumentMatchers.anyInt())).willReturn(null);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/categories/{id}/products", 1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Exception resolvedException = mvcResult.getResolvedException();
        Assertions.assertThat(resolvedException)
                .isNotNull()
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(messageSource.getMessage("CATEGORY_NOT_FOUND", new Object[]{}, Locale.ENGLISH));
    }

    private List<Category> populateRandomCategories() {
        return Arrays.asList(
                new Category(1, faker.code().toString(), faker.name().name()),
                new Category(2, faker.code().toString(), faker.name().name()),
                new Category(3, faker.code().toString(), faker.name().name())
        );
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