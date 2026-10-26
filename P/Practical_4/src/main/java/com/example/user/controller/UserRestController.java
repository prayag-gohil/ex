package com.example.user.controller;


import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User saveUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }

    @GetMapping("/{userId}")
    public Optional<User> showUser(@PathVariable Integer userId)
    {
        return userRepository.findById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@RequestBody User user,@PathVariable Integer userId)
    {
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId)
    {
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
