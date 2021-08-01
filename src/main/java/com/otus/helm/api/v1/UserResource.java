package com.otus.helm.api.v1;


import com.otus.helm.entity.User;
import com.otus.helm.repository.UserRepository;
import liquibase.pro.packaged.U;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserResource {

    private final UserRepository userRepository;

    @GetMapping("/user/{name}")
    public User getUser(@PathVariable String name) {
        return userRepository.findByUsername(name);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/user/{name}")
    public void deleteUser(@PathVariable String name) {
        userRepository.deleteById(Long.valueOf(name));
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable String id) {
        Optional<User> existing = userRepository.findById(Long.valueOf(id));

        if (existing.isEmpty()) {
            return userRepository.save(user);
        }

        User updatedUser = existing.get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setLastname(user.getLastname());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhone(user.getPhone());

        return userRepository.save(updatedUser);
    }
}
