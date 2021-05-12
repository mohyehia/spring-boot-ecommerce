package com.mohyehia.ecommerce.service;

import com.github.javafaker.Faker;
import com.mohyehia.ecommerce.dao.UserDAO;
import com.mohyehia.ecommerce.entity.User;
import com.mohyehia.ecommerce.service.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    private static Faker faker;

    @BeforeAll
    static void initializeFaker() {
        faker = new Faker(Locale.ENGLISH);
    }

    @Test
    void when_calling_save_function_then_user_is_saved_successfully() {
        User user = populateRandomUser();
        BDDMockito.given(userDAO.save(Mockito.any(User.class))).willReturn(user);
        User savedUser = userService.save(user);
        Assertions.assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
    }

    private User populateRandomUser() {
        User user = new User();
        user.setUsername(faker.name().username());
        user.setEmail(faker.internet().emailAddress());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(Collections.emptySet());
        return user;
    }
}
