package com.poc.redis.services.impl;

import com.poc.redis.entity.User;
import com.poc.redis.error.UserNotFoundException;
import com.poc.redis.repository.UserRepository;
import com.poc.redis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    @CachePut(value = "user", key = "#user.id")
    public User updateUser(User user) {
        User u = repository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        u.setAge(user.getAge());
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setSurname(user.getSurname());
        return repository.save(u);
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public User getOneUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    @Cacheable(value = "user")
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public void deleteUser(Long id) {
        User u = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        repository.delete(u);
    }
}
