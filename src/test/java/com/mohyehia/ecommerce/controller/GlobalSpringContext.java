package com.mohyehia.ecommerce.controller;

import com.github.javafaker.Faker;
import com.mohyehia.ecommerce.service.framework.CategoryService;
import com.mohyehia.ecommerce.service.framework.ProductService;
import com.mohyehia.ecommerce.service.framework.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class GlobalSpringContext {
    @MockBean
    UserService userService;

    @MockBean
    CategoryService categoryService;

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MessageSource messageSource;

    static Faker faker;

    @BeforeAll
    static void initializeFaker() {
        faker = new Faker(Locale.ENGLISH);
    }
}
