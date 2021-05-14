package com.mohyehia.ecommerce.service;

import com.mohyehia.ecommerce.model.entity.User;
import com.mohyehia.ecommerce.service.framework.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    private final UserService userService;

    public AuthenticationService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsernameOrEmail(username, username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username or email: " + username);
        }
        new AccountStatusUserDetailsChecker().check(user);
        return user;
    }
}
