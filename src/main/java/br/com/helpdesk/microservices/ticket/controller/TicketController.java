package br.com.helpdesk.microservices.ticket.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helpdesk.microservices.ticket.dto.TicketDTO;
import br.com.helpdesk.microservices.ticket.model.Ticket;
import br.com.helpdesk.microservices.ticket.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createTicket(@RequestBody TicketDTO ticketDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ticketDTO.setApplicantUsername(authentication.getName());
		Ticket ticket = ticketService.FromDTO(ticketDTO);
		ticket = ticketService.createTicket(ticket);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ticket.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
