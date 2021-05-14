package com.mohyehia.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohyehia.ecommerce.constant.AppConstants;
import com.mohyehia.ecommerce.exception.ConflictException;
import com.mohyehia.ecommerce.model.api.request.SignupRequest;
import com.mohyehia.ecommerce.model.entity.Role;
import com.mohyehia.ecommerce.model.entity.User;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SignupControllerSpringContext extends GlobalSpringContext {

    @Test
    @DisplayName("Test adding new user with valid fields")
    void when_calling_signup_endpoint_with_valid_user_then_success_and_return_201_response() throws Exception {
        SignupRequest signupRequest = populateRandomSignupRequest();
        User user = populateValidUser(signupRequest);
        BDDMockito.given(userService.findByUsername(ArgumentMatchers.anyString())).willReturn(null);
        BDDMockito.given(userService.findByEmail(ArgumentMatchers.anyString())).willReturn(null);
        BDDMockito.given(userService.save(Mockito.any(User.class))).willReturn(user);
        mockMvc.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signupRequest))
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", CoreMatchers.equalTo(messageSource.getMessage("SIGNUP_REQUEST_USER_CREATED_SUCCESSFULLY", new Object[]{}, Locale.ENGLISH))));
    }

    @Test
    @DisplayName("Test saving user with null values")
    void when_calling_signup_endpoint_with_null_values_then_return_bad_request() throws Exception {
        SignupRequest signupRequest = populateInvalidSignupRequest();
        mockMvc.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signupRequest))
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test saving new user with username already exists")
    void when_Calling_signup_endpoint_with_exists_username_return_conflict() throws Exception {
        SignupRequest signupRequest = populateRandomSignupRequest();
        BDDMockito.given(userService.findByUsername(ArgumentMatchers.anyString())).willReturn(populateRandomUser(signupRequest.getUsername(), signupRequest.getEmail()));
        User retrievedUser = userService.findByUsername(signupRequest.getUsername());
        Assertions.assertThat(retrievedUser).isNotNull();
        Assertions.assertThat(retrievedUser.getUsername()).isEqualTo(signupRequest.getUsername());
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signupRequest))
        ).andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Exception resolvedException = mvcResult.getResolvedException();
        Assertions.assertThat(resolvedException)
                .isNotNull()
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(messageSource.getMessage("SIGNUP_REQUEST_USERNAME_ALREADY_EXISTS", new Object[]{}, Locale.ENGLISH));
    }

    @Test
    @DisplayName("Test saving new user with email address already exists")
    void when_calling_signup_endpoint_with_exists_email_address_then_return_conflict() throws Exception {
        SignupRequest signupRequest = populateRandomSignupRequest();
        BDDMockito.given(userService.findByEmail(ArgumentMatchers.anyString())).willReturn(populateRandomUser(signupRequest.getUsername(), signupRequest.getEmail()));
        User retrievedUser = userService.findByEmail(signupRequest.getEmail());
        Assertions.assertThat(retrievedUser).isNotNull();
        Assertions.assertThat(retrievedUser.getEmail()).isEqualTo(signupRequest.getEmail());
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signupRequest))
        ).andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Exception resolvedException = mvcResult.getResolvedException();
        Assertions.assertThat(resolvedException)
                .isNotNull()
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(messageSource.getMessage("SIGNUP_REQUEST_EMAIL_ADDRESS_ALREADY_EXISTS", new Object[]{}, Locale.ENGLISH));
    }

    private SignupRequest populateRandomSignupRequest() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername(faker.name().username());
        signupRequest.setEmail(faker.internet().emailAddress());
        signupRequest.setFirstName("Mohammed");
        signupRequest.setLastName("Mahmoud");
        signupRequest.setPassword("P@ssw0rd123");
        signupRequest.setConfirmPassword("P@ssw0rd123");
        return signupRequest;
    }

    private User populateValidUser(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setPassword(signupRequest.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(AppConstants.ROLE_CUSTOMER));
        user.setRoles(roles);
        return user;
    }

    private SignupRequest populateInvalidSignupRequest() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername(faker.name().username());
        signupRequest.setEmail(faker.internet().emailAddress());
        signupRequest.setFirstName(faker.name().firstName());
        return signupRequest;
    }

    private User populateRandomUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        return user;
    }
}
