package br.com.helpdesk.microservices.ticket.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class TicketDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Descrição preenchimento obrigatório")
	private String description;
	
	@NotEmpty(message = "Título preenchimento obrigatório")
	private String title;
	
	@NotEmpty(message = "Solicitante preenchimento obrigatório")
	private String applicantUsername;
	
	@NotEmpty(message = "E-mail do solicitante preenchimento obrigatório")
	@Email
	private String applicantEmail;
	
	@NotEmpty
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

	public String getApplicantUsername() {
		return applicantUsername;
	}

	public void setApplicantUsername(String applicantUsername) {
		this.applicantUsername = applicantUsername;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
