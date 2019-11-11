package com.chad.blogwebapp.controller;

import com.chad.blogwebapp.model.Picture;
import com.chad.blogwebapp.model.User;
import com.chad.blogwebapp.repository.AmazonS3ClientService;
import com.chad.blogwebapp.repository.PictureRepository;
import com.chad.blogwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileHandlerController {

    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file)
    {
        this.amazonS3ClientService.uploadFileToS3Bucket(file, true);

        Map<String, String> response = new HashMap<>();

        response.put("message", "https://family-blog-bucket.s3.us-east-2.amazonaws.com/" + file.getOriginalFilename());

        Picture pic = new Picture("https://family-blog-bucket.s3.us-east-2.amazonaws.com/" + file.getOriginalFilename());
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(a.getName());

        pic.setUser(user);
        pictureRepository.save(pic);

        return response;
    }

    @DeleteMapping
    public Map<String, String> deleteFile(@RequestParam("file_name") String fileName)
    {
        this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + fileName + "] removing request submitted successfully.");

        return response;
    }
}