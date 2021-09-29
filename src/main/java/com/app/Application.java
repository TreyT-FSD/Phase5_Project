package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//automatically kicks off the dispatcher service
		SpringApplication.run(Application.class, args);
	}

}
