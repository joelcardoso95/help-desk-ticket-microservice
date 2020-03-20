package br.com.helpdesk.microservices.ticket.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class TicketUpdateDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Resolução preenchimento obrigatório")
	private String resolution;
	
	public TicketUpdateDTO() {
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

}
