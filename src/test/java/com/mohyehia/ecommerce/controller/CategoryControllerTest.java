package com.mohyehia.ecommerce.controller;

import com.github.javafaker.Faker;
import com.mohyehia.ecommerce.model.entity.Category;
import com.mohyehia.ecommerce.exception.ResourceNotFoundException;
import com.mohyehia.ecommerce.service.framework.CategoryService;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MessageSource messageSource;

    @MockBean
    private CategoryService categoryService;

    private static Faker faker;

    @BeforeAll
    static void initializeFaker() {
        faker = new Faker(Locale.ENGLISH);
    }

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

    private List<Category> populateRandomCategories() {
        return Arrays.asList(
                new Category(1, faker.code().toString(), faker.name().name()),
                new Category(2, faker.code().toString(), faker.name().name()),
                new Category(3, faker.code().toString(), faker.name().name())
        );
    }
}