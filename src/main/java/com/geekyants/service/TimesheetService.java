package com.geekyants.service;

import com.geekyants.dto.CommitRequest;
import com.geekyants.entity.TimesheetFillRequest;

public interface TimesheetService {

	public TimesheetFillRequest processRequest(CommitRequest commitRequest);
	
}
