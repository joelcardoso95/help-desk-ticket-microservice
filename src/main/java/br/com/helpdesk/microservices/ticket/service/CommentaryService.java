package br.com.helpdesk.microservices.ticket.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpdesk.microservices.ticket.client.SecurityClient;
import br.com.helpdesk.microservices.ticket.dto.CommentaryDTO;
import br.com.helpdesk.microservices.ticket.dto.UserDTO;
import br.com.helpdesk.microservices.ticket.model.Commentary;
import br.com.helpdesk.microservices.ticket.model.Ticket;
import br.com.helpdesk.microservices.ticket.repository.CommentaryRepository;
import br.com.helpdesk.microservices.ticket.service.exceptions.ObjectNotFoundException;

@Service
public class CommentaryService {
	
	@Autowired
	private CommentaryRepository commentaryRepository;
	
	@Autowired
	private SecurityClient securityClient;
	
	@Autowired
	private TicketService ticketService;
	
	public Commentary createCommentary(Commentary commentary) {
		commentary.setId(null);
		return commentaryRepository.save(commentary);
	}
	
	public List<Commentary> findAll() {
		return commentaryRepository.findAll();
	}
	
	public Commentary findById(Long id) {
		Optional<Commentary> commentaryOptional = commentaryRepository.findById(id);
		
		if (commentaryOptional.isEmpty()) {
			throw new ObjectNotFoundException("Comentário não encontrado");
		}
		
		return commentaryOptional.get();
	}
	
	public void deleteCommentary(Long id) throws Exception {
		findById(id);
		
		try {
			commentaryRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	public Commentary fromDTO(CommentaryDTO commentaryDTO) {
		Ticket ticket = ticketService.find(commentaryDTO.getTicketId());
		UserDTO userDTO = securityClient.loadUserInfo();
		Date dateTime = new Date();

		Commentary commentary = new Commentary(null, dateTime, userDTO.getUsername(), commentaryDTO.getMessage(), ticket);
		return commentary;
	}
}
