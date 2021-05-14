package com.mohyehia.ecommerce.model.api.response;

import com.mohyehia.ecommerce.model.entity.User;
import lombok.Data;

@Data
public class SignupResponse {
    private String message;
    private User user;
}
