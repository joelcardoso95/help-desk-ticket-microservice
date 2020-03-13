package br.com.helpdesk.microservices.ticket.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.helpdesk.microservices.ticket.client.SecurityClient;
import br.com.helpdesk.microservices.ticket.dto.TicketDTO;
import br.com.helpdesk.microservices.ticket.dto.UserDTO;
import br.com.helpdesk.microservices.ticket.model.Category;
import br.com.helpdesk.microservices.ticket.model.Ticket;
import br.com.helpdesk.microservices.ticket.repository.CategoryRepository;
import br.com.helpdesk.microservices.ticket.repository.TicketRepository;
import br.com.helpdesk.microservices.ticket.service.exceptions.ForbiddenException;
import br.com.helpdesk.microservices.ticket.service.exceptions.ObjectNotFoundException;

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
	
	public Ticket updateTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
	public Ticket find(Long id) {
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		
		if (ticketOptional.isEmpty()) {
			throw new ObjectNotFoundException("Ticket Não Encontrado");
		}
		
		return ticketOptional.get();
	}
	
	public List<Ticket> findByUser() {
		boolean isAdmin = hasRole("role_admin");
		
		UserDTO userDTO = securityClient.loadUserInfo();
		
		if (isAdmin) {
			return ticketRepository.findByAssigneeUsername(userDTO.getUsername());
		}
		
		return ticketRepository.findByApplicantUsername(userDTO.getUsername());
	}
	
	public Page<Ticket> findAll(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		boolean isAdmin = hasRole("role_admin");

		if (!isAdmin) {
			throw new ForbiddenException("Access Denied");
		}

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
	
	public Ticket FromUpdateDTO(Long id) {
		Ticket ticket = find(id);
		UserDTO userDTO = securityClient.loadUserInfo();
		ticket.setAssigneeUsername(userDTO.getUsername());
		ticket.setAssigneeEmail(userDTO.getEmail());
		return ticket;
	}
	
	public boolean hasRole(String role) {
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		boolean hasRole = false;
		for (GrantedAuthority grantedAuthority : authorities) {
			hasRole = grantedAuthority.getAuthority().equals(role);
			if (hasRole) {
				break;
			}
		}
		
		return hasRole;
	}
}
