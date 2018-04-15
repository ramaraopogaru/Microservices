package com.nareshit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/getHomePage")
	public  String getHomePage() {
		return "home";
	}
	
	@RequestMapping("/getLoginPage")
	public String getLoginPage() {
		
		return "login";
	}
	
	@RequestMapping("/getDashboard")
	public String getDashBorad() {
		return "body";
	}
}
