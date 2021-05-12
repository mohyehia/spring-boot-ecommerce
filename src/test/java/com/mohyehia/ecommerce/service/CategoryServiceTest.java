package com.mohyehia.ecommerce.service;

import com.github.javafaker.Faker;
import com.mohyehia.ecommerce.entity.Category;
import com.mohyehia.ecommerce.service.framework.CategoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryService categoryService;

    private static Faker faker;

    @BeforeAll
    static void initializeFaker() {
        faker = new Faker(Locale.ENGLISH);
    }

    @Test
    @DisplayName("test retrieving all categories")
    void when_calling_findAll_method_then_return_all_categories() {
        Category category1 = new Category(1, faker.code().toString(), faker.name().name());
        Category category2 = new Category(2, faker.code().toString(), faker.name().name());
        Category category3 = new Category(3, faker.code().toString(), faker.name().name());
        List<Category> categories = Arrays.asList(category1, category2, category3);
        BDDMockito.given(categoryService.findAll()).willReturn(categories);
        Assertions.assertThat(categoryService.findAll())
                .hasSize(3)
                .contains(category1, category2, category3)
                .doesNotContainNull();
    }

    @Test
    @DisplayName("test retrieving one category by id")
    void when_calling_findById_method_then_return_only_one_category() {
        Category category = new Category(1, faker.code().toString(), faker.name().name());
        BDDMockito.given(categoryService.findById(ArgumentMatchers.anyInt())).willReturn(category);
        Category retrievedCategory = categoryService.findById(1);
        Assertions.assertThat(retrievedCategory).isNotNull();
        Assertions.assertThat(retrievedCategory.getName()).isEqualTo(category.getName());
    }

    @Test
    @DisplayName("test retrieving a category with not found id then return null")
    void when_calling_findById_method_with_not_found_id_then_return_null() {
        BDDMockito.given(categoryService.findById(ArgumentMatchers.anyInt())).willReturn(null);
        Category retrievedCategory = categoryService.findById(1);
        Assertions.assertThat(retrievedCategory).isNull();
    }
}