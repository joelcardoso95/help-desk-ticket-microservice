package br.com.helpdesk.microservices.ticket.dto;

import java.io.Serializable;

public class TicketUpdateDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String assigneeUsername;
	
	private String assigneeEmail;
	
	public TicketUpdateDTO() {
	}

	public String getAssigneeUsername() {
		return assigneeUsername;
	}

	public void setAssigneeUsername(String assigneeUsername) {
		this.assigneeUsername = assigneeUsername;
	}

	public String getAssigneeEmail() {
		return assigneeEmail;
	}

	public void setAssigneeEmail(String assigneeEmail) {
		this.assigneeEmail = assigneeEmail;
	}

}
