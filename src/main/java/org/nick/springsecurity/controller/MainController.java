package org.nick.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MainController {
	
	List <String> names = Arrays.asList("John", "Sarrah", "O'Connor");
	
	@GetMapping("/")
	public String home(){
		return "Home page";
	}
	
	@GetMapping("/authenticated")
	public String pageForAuthenticatedUsers(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return "Secured part of Web Service\n "+auth;
	}
	
	@GetMapping("/read_profile")
	public String sayHello(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return "Hey, here is profile of ---> '  ' " + auth;
	}
	
	@GetMapping("/only_for_admins")
	public String adminPage(){
		return "Hey ho, it's ADMINKA!;))";
	}
	
}
