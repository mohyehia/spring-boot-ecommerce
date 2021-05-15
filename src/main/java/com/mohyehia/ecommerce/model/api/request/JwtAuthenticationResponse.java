package com.mohyehia.ecommerce.model.api.request;

import com.mohyehia.ecommerce.model.api.response.UserProfile;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String tokenType;
    private UserProfile userProfile;

    public JwtAuthenticationResponse(String token, UserProfile userProfile) {
        this.tokenType = "Bearer";
        this.token = token;
        this.userProfile = userProfile;
    }
}
