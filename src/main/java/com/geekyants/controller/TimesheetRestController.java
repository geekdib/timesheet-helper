package com.geekyants.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geekyants.dto.CommitRequest;
import com.geekyants.entity.TimesheetFillRequest;
import com.geekyants.service.TimesheetService;

@RestController
public class TimesheetRestController {
	
	
	@Autowired
	private TimesheetService service;

	@PostMapping("/invokeFillTimesheet")
	public ResponseEntity<Object> invokeFillTimesheet(@RequestBody CommitRequest request) throws IOException{
		
		TimesheetFillRequest fillRequest = service.processRequest(request);
		
		Map<String, Object> resmap = new HashMap<>();
		resmap.put("email", fillRequest.getEmail());
		resmap.put("logHours", fillRequest.getLogHours());
		resmap.put("Description", fillRequest.getDescription());
		resmap.put("date", fillRequest.getDate());
		resmap.put("JIRA_TICKET_ID", fillRequest.getJIRA_TICKET_ID());
		
		return new ResponseEntity<>(resmap, HttpStatus.OK);
		
	}
	
}
