package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tehran"));
		System.out.println(now+"################################################################################################");
		System.out.println(now);
	}

}
