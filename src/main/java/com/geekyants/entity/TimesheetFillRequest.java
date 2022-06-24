package com.geekyants.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="timesheet")
public class TimesheetFillRequest {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private int logHours;
	private String Description;
	private String date;
	private String JIRA_TICKET_ID;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLogHours() {
		return logHours;
	}
	public void setLogHours(int logHours) {
		this.logHours = logHours;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getJIRA_TICKET_ID() {
		return JIRA_TICKET_ID;
	}
	public void setJIRA_TICKET_ID(String jIRA_TICKET_ID) {
		JIRA_TICKET_ID = jIRA_TICKET_ID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TimesheetFillRequest [id=" + id + ", email=" + email + ", logHours=" + logHours + ", Description="
				+ Description + ", date=" + date + ", JIRA_TICKET_ID=" + JIRA_TICKET_ID + ", status=" + status + "]";
	}

	
	
}
