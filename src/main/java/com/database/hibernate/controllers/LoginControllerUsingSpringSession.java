package com.database.hibernate.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginControllerUsingSpringSession {

	@GetMapping("/login/spring-session")
	public ResponseEntity<List<String>> getLogin(HttpServletRequest req,@RequestParam String name,@RequestParam(name="P") String password) {
		System.out.println("NAME ="+name);
		if(name.equals("vikas")) {
			HttpSession session = req.getSession();
			List<String> vikasMessages = null;
			if(session.getAttribute("name")==null) {
				System.out.println("Creating Vikas Session.");
				vikasMessages = new ArrayList<String>(Arrays.asList("MESSAGEVIKAS1","MESSAGEVIKAS2"));
				session.setAttribute("name",name);
				session.setAttribute(name, vikasMessages);
			}
			else
			{
				vikasMessages = (List<String>)session.getAttribute(name);
			}
			return new ResponseEntity(vikasMessages,HttpStatus.OK);
		}
		else if(name.equals("vicky")) {
			HttpSession session = req.getSession();
			List<String> vickyMessages  = null;
			if(session.getAttribute("name")==null) {
				System.out.println("Creating Vicky Session.");
				vickyMessages = new ArrayList<String>(Arrays.asList("VICKY-MESSAGE","VICKY-MESSAGE"));
				session.setAttribute("name",name);
				session.setAttribute(name, vickyMessages );
			}
			else
			{
				vickyMessages  = (List<String>)session.getAttribute(name);
			}
			return new ResponseEntity(vickyMessages,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<String>>(new ArrayList(Arrays.asList("Un-Authenticated Request")), HttpStatus.UNAUTHORIZED);
		}
	}
}
