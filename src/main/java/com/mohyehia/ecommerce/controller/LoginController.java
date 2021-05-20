package com.mohyehia.ecommerce.controller;

import com.mohyehia.ecommerce.model.api.response.JwtAuthenticationResponse;
import com.mohyehia.ecommerce.model.api.request.LoginRequest;
import com.mohyehia.ecommerce.model.api.response.UserProfile;
import com.mohyehia.ecommerce.model.entity.User;
import com.mohyehia.ecommerce.service.AuthenticationService;
import com.mohyehia.ecommerce.utility.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = authenticationService.loadUserByUsername(loginRequest.getUsernameOrEmail());
        String jwtToken = jwtTokenProvider.generateToken(userDetails);
        return new ResponseEntity<>(new JwtAuthenticationResponse(jwtToken, populateUserProfileFromUserDetails(userDetails)), HttpStatus.OK);
    }

    private UserProfile populateUserProfileFromUserDetails(UserDetails userDetails) {
        User user = (User) userDetails;
        return new UserProfile(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName());
    }
}
