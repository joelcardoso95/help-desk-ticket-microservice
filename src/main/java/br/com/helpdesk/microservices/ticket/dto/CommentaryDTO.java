package br.com.helpdesk.microservices.ticket.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentaryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento de mensagem obrigatório")
	private String message;
	
	@NotNull
	private Long ticketId;
	
	public CommentaryDTO() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
}
