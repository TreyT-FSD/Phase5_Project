package com.app.controller;

import java.time.LocalDateTime;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldRestController.class);
	
	@RequestMapping("/")
	public String sayHello() {
		logger.info("Hello World Rest Controller Triggered at " + LocalDateTime.now());
		return "Hello world Rest Controller docker test";
	}

}
