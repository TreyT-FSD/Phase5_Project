package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hw")
public class HelloWorldController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldRestController.class);

	//TODO: fix this, currently doesnt work. how to do JSP with SpringBoot?
	@RequestMapping("/")
	public String HelloWorld() {
		logger.info("Hello World Controller Triggered at " + LocalDateTime.now());
		return "HelloWorldJSP";
	}

}
