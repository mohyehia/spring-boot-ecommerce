package com.mohyehia.ecommerce.entity.api.response;

import com.mohyehia.ecommerce.entity.User;
import lombok.Data;

@Data
public class SignupResponse {
    private String message;
    private User user;
}
