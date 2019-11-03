package com.chad.blogwebapp.controller;

import com.chad.blogwebapp.model.Post;
import com.chad.blogwebapp.model.User;
import com.chad.blogwebapp.repository.PostRepository;
import com.chad.blogwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class PostController {

    @Autowired
    private PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/posts")
    public Iterable<Post> getPosts() {
        return repository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/recentPosts")
    public Iterable<Post> findLastTwoPosts() {
        return repository.findLastTwoPost();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/posts")
    public void addPost(@RequestBody Post post) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(a.getName());
        Date date = new Date();
        post.setCreatedAt(date);

        post.setUser(user);

        this.repository.save(post);
    }
}

