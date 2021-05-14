package com.mohyehia.ecommerce.controller;

import com.mohyehia.ecommerce.model.entity.User;
import com.mohyehia.ecommerce.model.api.request.SignupRequest;
import com.mohyehia.ecommerce.model.api.response.SignupResponse;
import com.mohyehia.ecommerce.exception.ConflictException;
import com.mohyehia.ecommerce.service.framework.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/signup")
@Log4j2
@RequiredArgsConstructor
public class SignupController {
    private final UserService userService;
    private final MessageSource messageSource;

    @PostMapping
    public ResponseEntity<SignupResponse> registerNewUser(@Valid @RequestBody SignupRequest signupRequest,
                                                          Locale locale) {
        User user;
        user = userService.findByUsername(signupRequest.getUsername());
        if (user != null) {
            throw new ConflictException(messageSource.getMessage("SIGNUP_REQUEST_USERNAME_ALREADY_EXISTS", new Object[]{}, locale));
        }
        user = userService.findByEmail(signupRequest.getEmail());
        if (user != null) {
            throw new ConflictException(messageSource.getMessage("SIGNUP_REQUEST_EMAIL_ADDRESS_ALREADY_EXISTS", new Object[]{}, locale));
        }
        user = populateUserFromSignupRequest(signupRequest);
        user = userService.save(user);
        return new ResponseEntity<>(populateSignupResponse(user, locale), HttpStatus.CREATED);
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

    private SignupResponse populateSignupResponse(User user, Locale locale) {
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setMessage(messageSource.getMessage("SIGNUP_REQUEST_USER_CREATED_SUCCESSFULLY", new Object[]{}, locale));
        signupResponse.setUser(user);
        return signupResponse;
    }
}
