package com.chad.blogwebapp.controller;

import com.chad.blogwebapp.model.Picture;
import com.chad.blogwebapp.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PictureController {

    @Autowired
    private PictureRepository repository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pictures")
    public Iterable<Picture> getPicture() {
        return this.repository.findAll();
    }

}
