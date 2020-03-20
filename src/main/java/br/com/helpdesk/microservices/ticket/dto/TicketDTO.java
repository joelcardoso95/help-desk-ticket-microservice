package br.com.helpdesk.microservices.ticket.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TicketDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Descrição preenchimento obrigatório")
	private String description;
	
	@NotEmpty(message = "Título preenchimento obrigatório")
	private String title;
	
	@NotNull
	private Long categoryId;
	
	public TicketDTO() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
