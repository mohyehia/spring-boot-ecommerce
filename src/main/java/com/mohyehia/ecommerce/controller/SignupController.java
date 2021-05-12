package com.mohyehia.ecommerce.controller;

import com.mohyehia.ecommerce.entity.User;
import com.mohyehia.ecommerce.entity.api.request.SignupRequest;
import com.mohyehia.ecommerce.entity.api.response.SignupResponse;
import com.mohyehia.ecommerce.exception.ConflictException;
import com.mohyehia.ecommerce.service.framework.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/signup")
@Log4j2
@RequiredArgsConstructor
public class SignupController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<SignupResponse> registerNewUser(@Valid @RequestBody SignupRequest signupRequest) {
        User user;
        user = userService.findByUsername(signupRequest.getUsername());
        if (user != null) {
            throw new ConflictException("User with the same username already exists!");
        }
        user = userService.findByEmail(signupRequest.getEmail());
        if (user != null) {
            throw new ConflictException("User with the same email address already exists!");
        }
        user = populateUserFromSignupRequest(signupRequest);
        user = userService.save(user);
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setMessage("User created successfully!");
        signupResponse.setUser(user);
        return new ResponseEntity<>(signupResponse, HttpStatus.CREATED);
    }

    private User populateUserFromSignupRequest(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setPassword(signupRequest.getPassword());
        return user;
    }
}
