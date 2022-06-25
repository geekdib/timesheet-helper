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
		this.error = crmsException.getError();
		this.message = crmsException.getMessage();
		this.path = crmsException.getPath();
	}
	
	private Date timestamp;
	private HttpStatus status;
	private String error;
	private String message;
	private String path;
	
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "TimesheetExceptionResponse [timestamp=" + timestamp + ", status=" + status + ", error=" + error
				+ ", message=" + message + ", path=" + path + "]";
	}
}
