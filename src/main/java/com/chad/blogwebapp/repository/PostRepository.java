package com.chad.blogwebapp.repository;

import com.chad.blogwebapp.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface PostRepository extends CrudRepository<Post, Long> {

    @Query(value = "SELECT * FROM post ORDER BY id DESC LIMIT 2", nativeQuery = true)
    List<Post> findLastTwoPost();
}