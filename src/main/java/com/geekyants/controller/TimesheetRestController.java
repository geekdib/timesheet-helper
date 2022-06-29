package com.geekyants.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekyants.dto.CommitRequest;
import com.geekyants.entity.TimesheetFillRequest;
import com.geekyants.entity.TimesheetUrl;
import com.geekyants.exception.TimesheetException;
import com.geekyants.repository.TimesheetUrlRepository;
import com.geekyants.service.TimesheetService;

/**
 * @author @dibyapp, Name : Dibyaprakash, Email : dibyaprakash@geekyants.com
 * @Project : timesheet-helper
 */
@RestController
public class TimesheetRestController {

	@Autowired
	private TimesheetUrlRepository timesheetUrlRepository;

	@Autowired
	private TimesheetService service;

	@PostMapping("/invokeFillTimesheet")
	public ResponseEntity<Object> invokeFillTimesheet(@RequestBody(required = false) CommitRequest request) {
		if (request == null || request.getCommits() == null || request.getCommits().isEmpty()) {
			throw new TimesheetException(HttpStatus.OK, "Successfully invoked the endpoint!");
		}
		TimesheetFillRequest fillRequest = service.processRequest(request);
		Map<String, Object> resmap = new HashMap<>();
		if (fillRequest.getStatus().equalsIgnoreCase("Success")) {
			resmap.put("message", "Timesheet successfully logged for " + fillRequest.getLogHours() + " hours");
		} else {
			throw new TimesheetException(HttpStatus.OK, "Timesheet didn't get logged - " + fillRequest.getStatus());
		}
		return new ResponseEntity<>(resmap, HttpStatus.OK);
	}

	@PostMapping("/updateTimesheetUrl")
	public int updateTimesheetUrl(@RequestParam("url") String url) {

		TimesheetUrl timesheetUrl = new TimesheetUrl();
		timesheetUrl.setUrl(url);
		timesheetUrl.setId(1);
		timesheetUrlRepository.save(timesheetUrl);

		return 1;
	}
}
