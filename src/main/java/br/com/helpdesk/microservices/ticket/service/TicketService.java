package br.com.helpdesk.microservices.ticket.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.helpdesk.microservices.ticket.client.SecurityClient;
import br.com.helpdesk.microservices.ticket.dto.TicketDTO;
import br.com.helpdesk.microservices.ticket.dto.UserDTO;
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
	
	@Autowired
	private SecurityClient securityClient;
	
	@Transactional
	public Ticket createTicket(Ticket ticket) {
		ticket.setId(null);
		return ticketRepository.save(ticket);
	}
	
	public Page<Ticket> findAll(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return ticketRepository.findAll(pageRequest);
	}
	
	public Ticket FromDTO (TicketDTO ticketDTO) {
		Optional<Category> optionalCategory = categoryRepository.findById(ticketDTO.getCategoryId());
		Category category = optionalCategory.get();
		Date openedAt = new Date();
		UserDTO userDTO = securityClient.loadUserInfo();
		ticketDTO.setApplicantUsername(userDTO.getUsername());
		ticketDTO.setApplicantEmail(userDTO.getEmail());
		Ticket ticket = new Ticket(null, ticketDTO.getDescription(), ticketDTO.getTitle(), ticketDTO.getApplicantUsername(), ticketDTO.getApplicantEmail(), openedAt, category);
		return ticket;
	}
}
