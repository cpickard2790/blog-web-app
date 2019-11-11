package com.chad.blogwebapp;

import com.chad.blogwebapp.model.Picture;
import com.chad.blogwebapp.model.Post;
import com.chad.blogwebapp.model.Task;
import com.chad.blogwebapp.model.User;
import com.chad.blogwebapp.repository.PictureRepository;
import com.chad.blogwebapp.repository.PostRepository;
import com.chad.blogwebapp.repository.TaskRepository;
import com.chad.blogwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;

@SpringBootApplication
public class BlogWebAppApplication {

	@Autowired
	private UserRepository urepository;


	@Autowired
	private PostRepository prepository;

	@Autowired
	private TaskRepository trepository;

	@Autowired
	private PictureRepository pictureRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogWebAppApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			User user1 = new User("Chad", "Pickard", "cpickard2790@gmail.com", "cpickard2790", BCrypt.hashpw("password", BCrypt.gensalt()), "admin");
			User user2 = new User("Bobby", "Lee", "blee@yahoo.com", "bobbylee19", BCrypt.hashpw("pass1234", BCrypt.gensalt()), "user");
			Date date = new Date();
			/*
			Post post1 = new Post("First Post", "This is my very first post", date, "https://cdn.pixabay.com/photo/2019/09/19/07/26/extreme-4488462_960_720.jpg", user1);
			Post post2 = new Post("Second Post", "This would be my seoncd post", date, "https://cdn.pixabay.com/photo/2015/03/26/10/01/dirt-bike-690770_960_720.jpg", user1);
			Post post3 = new Post("Third Post", "And this would be my third post", date, "https://family-blog-bucket.s3.us-east-2.amazonaws.com/c%23.jpg",  user2);
			Post post4 = new Post("Fourth Post", "And this would be my fourth post", date, "https://family-blog-bucket.s3.us-east-2.amazonaws.com/164320142-geometry-wallpapers.jpg", user2);
			Task task1 = new Task("Motocross Race", "Beaver Creek MX", "2019-11-12", user1);
			Task task2 = new Task("Soccer Game", "YMCA Fields", "2019-11-18", user2);
			Picture pic1 = new Picture("https://family-blog-bucket.s3.us-east-2.amazonaws.com/c%23.jpg", user1);
			Picture pic2 = new Picture("https://family-blog-bucket.s3.us-east-2.amazonaws.com/164320142-geometry-wallpapers.jpg", user2);
			*/
			urepository.save(user1);
			urepository.save(user2);
			/*
			prepository.save(post1);
			prepository.save(post2);
			prepository.save(post3);
			prepository.save(post4);
			trepository.save(task1);
			trepository.save(task2);
			pictureRepository.save(pic1);
			pictureRepository.save(pic2);
			*/
		};
	}


}
