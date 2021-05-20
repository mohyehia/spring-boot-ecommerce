package com.mohyehia.ecommerce.service;

import com.github.javafaker.Faker;
import com.mohyehia.ecommerce.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private AuthenticationService authenticationService;
    private static Faker faker;

    @BeforeAll
    static void initializeFaker() {
        faker = new Faker(Locale.ENGLISH);
    }

    @Test
    @DisplayName("testing loading user by his username or email for authentication")
    void when_calling_loadUserByUsername_with_existing_username_or_email_address_then_return_user() {
        User user = populateRandomUser();
        BDDMockito.given(authenticationService.loadUserByUsername(ArgumentMatchers.anyString())).willReturn(user);
        UserDetails retrievedUser = authenticationService.loadUserByUsername(user.getUsername());
        Assertions.assertThat(retrievedUser).isNotNull();
        Assertions.assertThat(retrievedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    @DisplayName("testing loading user by non existing username or email for authentication")
    void when_calling_loadUserByUsername_with_non_existing_username_or_email_address_then_not_found_Exception_is_thrown() {
        BDDMockito.given(authenticationService.loadUserByUsername(ArgumentMatchers.anyString())).willThrow(new UsernameNotFoundException("User not found with username or email"));
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> authenticationService.loadUserByUsername(ArgumentMatchers.anyString()));
        Assertions.assertThat(exception).isInstanceOf(UsernameNotFoundException.class);
        assertTrue(exception.getMessage().contains("User not found with username or email"));
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