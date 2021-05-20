package com.mohyehia.ecommerce.model.api.request;

import com.mohyehia.ecommerce.validator.PasswordConfirmation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@PasswordConfirmation(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "{SIGNUP_REQUEST_PASSWORD_AND_CONFIRM_PASSWORD_NOT_IDENTICAL}"
)
@ApiModel(description = "Class representing a signup request to the application")
public class SignupRequest {
    @ApiModelProperty(notes = "Username", name = "username", required = true, example = "moh_yehia", position = 1)
    @NotNull(message = "{SIGNUP_REQUEST_USERNAME_CANNOT_BE_EMPTY}")
    @Size(min = 5, max = 50, message = "{SIGNUP_REQUEST_INVALID_USERNAME}")
    private String username;

    @ApiModelProperty(notes = "Email Address", name = "email", required = true, example = "moh@mail.com", position = 2)
    @NotNull(message = "{SIGNUP_REQUEST_EMAIL_CANNOT_BE_EMPTY}")
    @Email(message = "{SIGNUP_REQUEST_INVALID_EMAIL}")
    private String email;

    @ApiModelProperty(notes = "User Firstname", name = "firstName", required = true, example = "mohamed", position = 3)
    @NotNull(message = "{SIGNUP_REQUEST_FIRST_NAME_CANNOT_BE_EMPTY}")
    @Size(min = 5, max = 50, message = "{SIGNUP_REQUEST_INVALID_FIRST_NAME}")
    private String firstName;

    @ApiModelProperty(notes = "User Lastname", name = "lastName", required = true, example = "ahmed", position = 4)
    @NotNull(message = "{SIGNUP_REQUEST_LAST_NAME_CANNOT_BE_EMPTY}")
    @Size(min = 5, max = 50, message = "{SIGNUP_REQUEST_INVALID_LAST_NAME}")
    private String lastName;

    @ApiModelProperty(notes = "Password that will be used to login to he system", name = "password", required = true, example = "123456", position = 5)
    @NotNull(message = "{SIGNUP_REQUEST_PASSWORD_CANNOT_BE_EMPTY}")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}", message = "{SIGNUP_REQUEST_INVALID_PASSWORD}")
    private String password;

    @ApiModelProperty(notes = "Confirmation Password which must be the same as password", name = "password", required = true, example = "123456", position = 6)
    @NotNull(message = "{SIGNUP_REQUEST_CONFIRM_PASSWORD_CANNOT_BE_EMPTY}")
    private String confirmPassword;
}
