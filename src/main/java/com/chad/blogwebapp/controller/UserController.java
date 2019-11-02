package com.chad.blogwebapp.controller;

import com.chad.blogwebapp.model.User;
import com.chad.blogwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/getUser")
    @ResponseBody
    public User currentUser(@RequestParam("username") String username) {
        return repository.findByUsername(username);
    }

    @RequestMapping("/user")
    public SecurityContext user(Principal user) {
        SecurityContext context = SecurityContextHolder.getContext();
        return context;
    }

}
