package com.geekyants.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class TimesheetException extends RuntimeException{
	
	private static final long serialVersionUID = -5669712918023446031L;
	
	public TimesheetException(HttpStatus status, String error, String message, String path) {
		this.timestamp = new Date();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
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
		return "TimesheetException [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message="
				+ message + ", path=" + path + "]";
	}
}
