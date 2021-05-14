package com.mohyehia.ecommerce.service.framework;

import com.mohyehia.ecommerce.model.entity.User;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    User findByEmail(String email);
}
