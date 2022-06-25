package com.geekyants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.geekyants.entity.TimesheetUrl;
import com.geekyants.repository.TimesheetFillRequestRepository;
import com.geekyants.repository.TimesheetUrlRepository;

@Controller
public class TimesheetController {
	
	@Autowired
	private TimesheetFillRequestRepository fillRequestRepository;
	
	@Autowired
	private TimesheetUrlRepository timesheetUrlRepository;
	
	@GetMapping("/logs")
	public String getLogs(Model model) {
		model.addAttribute("timesheets" , fillRequestRepository.findAll());
		
		if(timesheetUrlRepository.findById(1)!=null) {
			model.addAttribute("timesheetUrl" , timesheetUrlRepository.findById(1));
		}else {
			model.addAttribute("timesheetUrl" , new TimesheetUrl());
		}
		return "home/index";
	}
	
	
	@GetMapping("/docs")
	public String getDocs(Model model) {
		return "docs/index";
	}
}
