package com.prokhelo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prokhelo.dao.UserRepository;
import com.prokhelo.entities.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prokhelo/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        
        return ResponseEntity.ok().body("Login Successfull");
    }
    
    @PostMapping
    public ResponseEntity<?> createUser(@Validated @RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        
        userRepository.save(user);
        return ResponseEntity.ok().body("User Created Successfull");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        
        if (existingUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        
        userRepository.save(existingUser);
        return ResponseEntity.ok().body("User Updated Successfull");
    }
    
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            return ResponseEntity.badRequest().body("No User found");
        }
        return ResponseEntity.ok(users);
    }
}
