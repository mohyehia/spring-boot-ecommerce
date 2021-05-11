package com.mohyehia.ecommerce.service.impl;

import com.mohyehia.ecommerce.dao.UserDAO;
import com.mohyehia.ecommerce.entity.User;
import com.mohyehia.ecommerce.service.framework.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }
}
