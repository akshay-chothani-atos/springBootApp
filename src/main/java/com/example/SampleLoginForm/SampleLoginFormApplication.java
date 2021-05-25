package com.example.SampleLoginForm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.SampleLoginForm.resource"})
public class SampleLoginFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleLoginFormApplication.class, args);
	}

}
