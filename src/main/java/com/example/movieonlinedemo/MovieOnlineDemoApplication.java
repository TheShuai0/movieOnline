package com.example.movieonlinedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.movieonlinedemo.mapper")
public class MovieOnlineDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieOnlineDemoApplication.class, args);
		System.out.println(8888);
	}

}
