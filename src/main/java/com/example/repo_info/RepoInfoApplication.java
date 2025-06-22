package com.example.repo_info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RepoInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepoInfoApplication.class, args);
	}

}
