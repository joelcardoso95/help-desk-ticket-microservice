package br.com.helpdesk.microservices.ticket.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		Ticket ticket = ticketService.FromDTO(ticketDTO);
		ticket = ticketService.createTicket(ticket);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ticket.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<Page<Ticket>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
												@RequestParam(value = "linesPerPage", defaultValue = "20") Integer linesInteger,
												@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
												@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Page<Ticket> tickets = ticketService.findAll(page, linesInteger, orderBy, direction);
		return ResponseEntity.ok().body(tickets);
	}
}
