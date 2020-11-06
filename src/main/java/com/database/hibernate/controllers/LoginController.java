package com.database.hibernate.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
	static int count=0;
	@GetMapping("/login")
	public ResponseEntity<String> login(HttpServletRequest request,@RequestParam String name,@RequestParam String password) {
		HttpSession session = request.getSession();
		if(session.getAttribute("name")==null) {
			System.out.println("Setting session for "+(++count)+" times.");
			System.out.println("SessionId being set = "+session.getId());
			session.setAttribute("name", name);
			return new ResponseEntity<String>("Session Expired Login again", HttpStatus.OK);
		}
		System.out.println("User entered Name :"+name+" and password :"+password);
		System.out.println("User name from session :"+session.getAttribute("name"));
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
