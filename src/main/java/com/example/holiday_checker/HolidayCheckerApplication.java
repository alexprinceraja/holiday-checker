package com.example.holiday_checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HolidayCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayCheckerApplication.class, args);
	}

}
