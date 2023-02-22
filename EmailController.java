package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.entity.EmailRequest;
import com.email.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	EmailService  emailservice;
	
	@RequestMapping ("/welcome")
	public String Welcome()
	{
		return "Welcome to Email API";
	}
	
	@RequestMapping (value= "/sendEmail", method = RequestMethod.POST)
	public ResponseEntity<?> Sendemail(@RequestBody EmailRequest request)
	{
		System.out.println(request);
		boolean result=this.emailservice.Sendemail(request.getMessage(),request.getSubject(),request.getTo());
		
		if(result==true)
		{
			return ResponseEntity.ok("Done...");
		}else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent");
		}
		
	}
	
}
