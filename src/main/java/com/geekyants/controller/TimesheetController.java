package com.geekyants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.geekyants.repository.TimesheetFillRequestRepository;

@Controller
public class TimesheetController {
	
	@Autowired
	private TimesheetFillRequestRepository fillRequestRepository;
	
	@GetMapping("/getLogs")
	public String getLogs(Model model) {
		model.addAttribute("timesheets" , fillRequestRepository.findAll());
		return "home/index";
	}
	
	
}
