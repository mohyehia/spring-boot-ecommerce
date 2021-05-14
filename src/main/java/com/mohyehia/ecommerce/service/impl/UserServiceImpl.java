package com.mohyehia.ecommerce.service.impl;

import com.mohyehia.ecommerce.constant.AppConstants;
import com.mohyehia.ecommerce.repository.UserRepository;
import com.mohyehia.ecommerce.model.entity.Role;
import com.mohyehia.ecommerce.model.entity.User;
import com.mohyehia.ecommerce.service.framework.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(AppConstants.ROLE_CUSTOMER));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
