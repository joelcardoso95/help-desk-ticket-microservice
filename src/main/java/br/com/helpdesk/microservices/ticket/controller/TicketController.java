package br.com.helpdesk.microservices.ticket.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helpdesk.microservices.ticket.dto.TicketDTO;
import br.com.helpdesk.microservices.ticket.dto.TicketUpdateDTO;
import br.com.helpdesk.microservices.ticket.model.Ticket;
import br.com.helpdesk.microservices.ticket.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping
	public ResponseEntity<Void> createTicket(@Valid @RequestBody TicketDTO ticketDTO) {
		Ticket ticket = ticketService.FromDTO(ticketDTO);
		ticket = ticketService.saveTicket(ticket);
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
	
	@PutMapping(value = "/takeOn/{id}")
	public ResponseEntity<Void> updateTicket(@PathVariable Long id) {
		Ticket ticket = ticketService.takeOnTicket(id);
		ticket = ticketService.saveTicket(ticket);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/opened")
	public ResponseEntity<List<Ticket>> getOpenedTickets() {
		Integer opened = 1;
		List<Ticket> tickets = ticketService.getTicketsByStatus(opened);
		return ResponseEntity.ok().body(tickets);
	}
	
	@GetMapping(value = "/closed")
	public ResponseEntity<List<Ticket>> getClosedTickets() {
		Integer closed = 2;
		List<Ticket> tickets = ticketService.getTicketsByStatus(closed);
		return ResponseEntity.ok().body(tickets);
	}
	
	@GetMapping(value = "/close/{id}")
	public ResponseEntity<Void> closeTicket(@Valid @RequestBody TicketUpdateDTO ticketUpdateDTO, @PathVariable Long id) {
		Ticket ticket = ticketService.closeTicket(ticketUpdateDTO, id);
		ticket = ticketService.saveTicket(ticket);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Ticket> findById(@PathVariable Long id) {
		Ticket ticket = ticketService.find(id);
		return ResponseEntity.ok().body(ticket);
	}
	
	@GetMapping(value = "/user")
	public ResponseEntity<List<Ticket>> findByUser() {
		List<Ticket> tickets = ticketService.findByUser();
		return ResponseEntity.ok().body(tickets);
	}
}
