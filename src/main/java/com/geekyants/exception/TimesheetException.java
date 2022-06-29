package com.geekyants.exception;
/**
 * @author @dibyapp, Name : Dibyaprakash, Email : dibyaprakash@geekyants.com
 * @Project : timesheet-helper
 */
import java.util.Date;

import org.springframework.http.HttpStatus;

public class TimesheetException extends RuntimeException{
	
	private static final long serialVersionUID = -5669712918023446031L;
	
	public TimesheetException(HttpStatus status, String message) {
		this.timestamp = new Date();
		this.status = status;
		this.message = message;
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
		return "TimesheetException [timestamp=" + timestamp + ", status=" + status + ", message=" + message + "]";
	}
}
