package com.poc.redis.services;

import com.poc.redis.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User updateUser(User user);
    User getOneUser(Long id);
    List<User> getAllUser();
    void deleteUser(Long id);
}
