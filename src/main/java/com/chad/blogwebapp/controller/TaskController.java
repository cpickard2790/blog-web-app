package com.chad.blogwebapp.controller;

import com.chad.blogwebapp.model.Task;
import com.chad.blogwebapp.model.User;
import com.chad.blogwebapp.repository.TaskRepository;
import com.chad.blogwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @GetMapping("/tasks/{id}")
    Optional<Task> one(@PathVariable Long id) {
        return this.repository.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/tasks/{id}")
    Task updateTask(@RequestBody Task updateTask, @PathVariable Long id) {
        return repository.findById(id)
                .map(task -> {
                    task.setDate(updateTask.getDate());
                    task.setEvent(updateTask.getEvent());
                    task.setLocation(updateTask.getLocation());
                    return repository.save(task);
                })
                .orElseGet(() -> {
                    updateTask.setId(id);
                    return repository.save(updateTask);
                });

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
