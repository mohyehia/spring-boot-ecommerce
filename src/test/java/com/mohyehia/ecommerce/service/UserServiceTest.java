package com.mohyehia.ecommerce.service;

import com.github.javafaker.Faker;
import com.mohyehia.ecommerce.model.entity.User;
import com.mohyehia.ecommerce.service.framework.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserService userService;

    private static Faker faker;

    @BeforeAll
    static void initializeFaker() {
        faker = new Faker(Locale.ENGLISH);
    }

    @Test
    @DisplayName("test when saving new user it will be saved to db")
    void when_calling_save_function_then_user_is_saved_successfully() {
        User user = populateRandomUser();
        BDDMockito.given(userService.save(Mockito.any(User.class))).willReturn(user);
        User savedUser = userService.save(user);
        Assertions.assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    @DisplayName("test finding user by username will return the user")
    void when_calling_find_by_exists_username_then_user_is_returned() {
        User user = populateRandomUser();
        BDDMockito.given(userService.findByUsername(ArgumentMatchers.anyString())).willReturn(user);
        User retrievedUser = userService.findByUsername(user.getUsername());
        Assertions.assertThat(retrievedUser).isNotNull();
        Assertions.assertThat(retrievedUser.getFirstName()).isEqualTo(user.getFirstName());
    }

    @Test
    @DisplayName("test finding user by email address will return the user")
    void when_calling_find_by_exists_email_address_then_user_is_returned() {
        User user = populateRandomUser();
        BDDMockito.given(userService.findByEmail(ArgumentMatchers.anyString())).willReturn(user);
        User retrievedUser = userService.findByEmail(user.getEmail());
        Assertions.assertThat(retrievedUser).isNotNull();
        Assertions.assertThat(retrievedUser.getUsername()).isEqualTo(user.getUsername());
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
