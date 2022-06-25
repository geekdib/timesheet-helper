package com.geekyants.service;
/**
 * @author @dibyapp, Name : Dibyaprakash, Email : dibyaprakash@geekyants.com
 * @Project : timesheet-helper
 */
import com.geekyants.dto.CommitRequest;
import com.geekyants.entity.TimesheetFillRequest;

public interface TimesheetService {

	public TimesheetFillRequest processRequest(CommitRequest commitRequest);
	
}
