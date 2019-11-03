package com.chad.blogwebapp.controller;

import com.chad.blogwebapp.model.Task;
import com.chad.blogwebapp.model.User;
import com.chad.blogwebapp.repository.TaskRepository;
import com.chad.blogwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/tasks")
    public Iterable<Task> getTask() {
        return this.repository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/tasks")
    public void addTask(@RequestBody Task task) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(a.getName());

        task.setUser(user);
        this.repository.save(task);
    }
}
