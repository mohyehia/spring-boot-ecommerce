package com.mohyehia.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.mohyehia.ecommerce.entity.api.request.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Locale;

@SpringBootTest
@AutoConfigureMockMvc
class SignupControllerTest {
    private Faker faker;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void initializeFaker() {
        faker = new Faker(Locale.ENGLISH);
    }

    @Test
    void when_calling_signup_endpoint_with_valid_user_then_success_and_return_201_response() throws Exception {
        SignupRequest signupRequest = populateRandomSignupRequest();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(signupRequest))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private SignupRequest populateRandomSignupRequest() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername(faker.name().username());
        signupRequest.setEmail(faker.internet().emailAddress());
        signupRequest.setFirstName(faker.name().firstName());
        signupRequest.setLastName(faker.name().lastName());
        signupRequest.setPassword(faker.internet().password(8, 20, true, true));
        signupRequest.setConfirmPassword(signupRequest.getPassword());
        return signupRequest;
    }
}
