package com.mohyehia.ecommerce.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserProfile {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
