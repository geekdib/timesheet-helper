package com.geekyants.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimesheetRestController {

	@PostMapping("/invokeFillTimesheet")
	public ResponseEntity<Object> invokeFillTimesheet(HttpServletRequest request){
		
		System.err.println("hitted");
		System.err.println(request.getParameterNames());
		
		return null;
	}
	
}
