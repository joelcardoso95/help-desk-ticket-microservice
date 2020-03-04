package br.com.helpdesk.microservices.ticket.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commentary")
public class Commentary implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime dateTime;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;

}
