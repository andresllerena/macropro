package com.macropro.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@Autowired
	private Logger logger;
	
	@GetMapping("/")
	public String showWelcome() {
		logger.info("Entering welcome screen.");
		return "welcome";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {
		logger.info("Access denied.");
		return "error";
	}
	
}
