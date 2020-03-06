package br.com.helpdesk.microservices.ticket.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpdesk.microservices.ticket.dto.TicketDTO;
import br.com.helpdesk.microservices.ticket.model.Category;
import br.com.helpdesk.microservices.ticket.model.Ticket;
import br.com.helpdesk.microservices.ticket.repository.CategoryRepository;
import br.com.helpdesk.microservices.ticket.repository.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	public Ticket createTicket(Ticket ticket) {
		ticket.setId(null);
		return ticketRepository.save(ticket);
	}
	
	public Ticket FromDTO (TicketDTO ticketDTO) {
		Optional<Category> optionalCategory = categoryRepository.findById(ticketDTO.getCategoryId());
		Category category = optionalCategory.get();
		LocalDateTime openedAt = LocalDateTime.now();
		Ticket ticket = new Ticket(null, ticketDTO.getDescription(), ticketDTO.getTitle(), ticketDTO.getApplicantUsername(), ticketDTO.getApplicantEmail(), null, null, openedAt, category);
		return ticket;
	}
}
