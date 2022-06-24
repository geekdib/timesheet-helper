package com.geekyants.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimesheetRestController {

	@PostMapping("/invokeFillTimesheet")
	public ResponseEntity<Object> invokeFillTimesheet(HttpServletRequest request) throws IOException{
		
		System.err.println("hitted");
		System.err.println(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		
		return null;
	}
	
}
