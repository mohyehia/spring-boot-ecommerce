package com.mohyehia.ecommerce.controller;

import com.mohyehia.ecommerce.entity.api.request.SignupRequest;
import com.mohyehia.ecommerce.entity.api.response.SignupResponse;
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
public class SignupController {
    @PostMapping
    public ResponseEntity<SignupResponse> registerNewUser(@Valid @RequestBody SignupRequest signupRequest){
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
