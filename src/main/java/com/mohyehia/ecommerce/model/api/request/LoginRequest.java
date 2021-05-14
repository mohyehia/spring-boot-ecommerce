package com.mohyehia.ecommerce.model.api.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @NotEmpty(message = "{LOGIN_REQUEST_USERNAME_OR_EMAIL_CANNOT_BE_EMPTY}")
    private String usernameOrEmail;

    @NotEmpty(message = "{LOGIN_REQUEST_PASSWORD_CANNOT_BE_EMPTY}")
    private String password;
}
