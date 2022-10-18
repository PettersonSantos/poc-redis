package com.poc.redis.controller;

import com.poc.redis.entity.User;
import com.poc.redis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/createUser")
    public User saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("/allUser")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(service.getAllUser());
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getOneUser(id);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }



}
