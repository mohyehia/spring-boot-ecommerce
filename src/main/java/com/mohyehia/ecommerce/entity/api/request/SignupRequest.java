package com.mohyehia.ecommerce.entity.api.request;

import com.mohyehia.ecommerce.validator.PasswordConfirmation;
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
public class SignupRequest {
    @NotNull(message = "{SIGNUP_REQUEST_USERNAME_CANNOT_BE_EMPTY}")
    @Size(min = 5, max = 50, message = "{SIGNUP_REQUEST_INVALID_USERNAME}")
    private String username;

    @NotNull(message = "{SIGNUP_REQUEST_EMAIL_CANNOT_BE_EMPTY}")
    @Email(message = "{SIGNUP_REQUEST_INVALID_EMAIL}")
    private String email;

    @NotNull(message = "{SIGNUP_REQUEST_FIRST_NAME_CANNOT_BE_EMPTY}")
    @Size(min = 5, max = 50, message = "{SIGNUP_REQUEST_INVALID_FIRST_NAME}")
    private String firstName;

    @NotNull(message = "{SIGNUP_REQUEST_LAST_NAME_CANNOT_BE_EMPTY}")
    @Size(min = 5, max = 50, message = "{SIGNUP_REQUEST_INVALID_LAST_NAME}")
    private String lastName;

    @NotNull(message = "{SIGNUP_REQUEST_PASSWORD_CANNOT_BE_EMPTY}")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}", message = "{SIGNUP_REQUEST_INVALID_PASSWORD}")
    private String password;

    @NotNull(message = "{SIGNUP_REQUEST_CONFIRM_PASSWORD_CANNOT_BE_EMPTY}")
    private String confirmPassword;
}
