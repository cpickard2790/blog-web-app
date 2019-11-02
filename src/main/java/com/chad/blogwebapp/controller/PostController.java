package com.chad.blogwebapp.controller;

import com.chad.blogwebapp.model.Post;
import com.chad.blogwebapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostRepository repository;

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
}

