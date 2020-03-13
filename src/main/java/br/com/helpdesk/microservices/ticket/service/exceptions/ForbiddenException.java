package br.com.helpdesk.microservices.ticket.service.exceptions;

public class ForbiddenException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ForbiddenException(String msg) {
		super(msg);
	}
	
	public ForbiddenException(String msg, Throwable cause) {
		super(msg, cause);
	}
}