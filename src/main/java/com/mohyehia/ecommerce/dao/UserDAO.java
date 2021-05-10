package com.mohyehia.ecommerce.dao;

import com.mohyehia.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
}
