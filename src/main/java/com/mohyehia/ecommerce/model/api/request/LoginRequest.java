package com.mohyehia.ecommerce.model.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "Class representing a login request to the application")
public class LoginRequest {
    @ApiModelProperty(notes = "Username or email address", name = "usernameOrEmail", required = true, example = "moh_yehia | moh@mail.com", position = 1)
    @NotEmpty(message = "{LOGIN_REQUEST_USERNAME_OR_EMAIL_CANNOT_BE_EMPTY}")
    private String usernameOrEmail;

    @ApiModelProperty(notes = "Password used at the signup process", name = "password", required = true, example = "123456", position = 2)
    @NotEmpty(message = "{LOGIN_REQUEST_PASSWORD_CANNOT_BE_EMPTY}")
    private String password;
}
