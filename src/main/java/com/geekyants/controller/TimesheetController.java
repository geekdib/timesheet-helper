package com.geekyants.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.geekyants.entity.TimesheetFillRequest;
import com.geekyants.entity.TimesheetUrl;
import com.geekyants.repository.TimesheetFillRequestRepository;
import com.geekyants.repository.TimesheetUrlRepository;
/**
 * @author @dibyapp, Name : Dibyaprakash, Email : dibyaprakash@geekyants.com
 * @Project : timesheet-helper
 */
@Controller
public class TimesheetController {
	
	@Autowired
	private TimesheetFillRequestRepository fillRequestRepository;
	
	@Autowired
	private TimesheetUrlRepository timesheetUrlRepository;
	
	@GetMapping("/logs")
	public String getLogs(Model model) {
		
		List<TimesheetFillRequest> all = fillRequestRepository.findAll();
		Collections.reverse(all);
		
		model.addAttribute("timesheets" , all);
		
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
