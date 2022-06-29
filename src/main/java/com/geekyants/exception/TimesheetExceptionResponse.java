package com.geekyants.exception;
/**
 * @author @dibyapp, Name : Dibyaprakash, Email : dibyaprakash@geekyants.com
 * @Project : timesheet-helper
 */
import java.util.Date;

import org.springframework.http.HttpStatus;

public class TimesheetExceptionResponse {
	
	public TimesheetExceptionResponse(TimesheetException crmsException) {
		this.timestamp = new Date();
		this.status = crmsException.getStatus();
		this.message = crmsException.getMessage();
	}
	
	private Date timestamp;
	private HttpStatus status;
	private String message;
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "TimesheetExceptionResponse [timestamp=" + timestamp + ", status=" + status + ", message=" + message
				+ "]";
	}
}
