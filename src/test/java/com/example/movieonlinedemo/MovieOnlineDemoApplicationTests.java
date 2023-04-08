package com.example.movieonlinedemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class MovieOnlineDemoApplicationTests {
	@Autowired
	private RedisTemplate redisTemplate;
	@Test
	void contextLoads() {

	}

	// redis测试
	@Test
	public void redisTest(){
		int a[] = new int[10];
		Object o = new Object();
		System.out.println(a[1]);
	}


}
