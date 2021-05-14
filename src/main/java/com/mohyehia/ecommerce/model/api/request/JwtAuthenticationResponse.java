package com.mohyehia.ecommerce.model.api.request;

import com.mohyehia.ecommerce.model.dto.UserDTO;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String tokenType;
    private UserDTO user;

    public JwtAuthenticationResponse(String token, UserDTO user) {
        this.tokenType = "Bearer";
        this.token = token;
        this.user = user;
    }
}
