package com.mohyehia.ecommerce.service.impl;

import com.mohyehia.ecommerce.dao.UserDAO;
import com.mohyehia.ecommerce.entity.User;
import com.mohyehia.ecommerce.service.framework.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }
}
