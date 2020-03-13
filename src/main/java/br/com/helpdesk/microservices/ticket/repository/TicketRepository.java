package br.com.helpdesk.microservices.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helpdesk.microservices.ticket.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	
	List<Ticket> findByAssigneeUsername(String assigneeUsername);
	
	List<Ticket> findByApplicantUsername(String applicantUsername);
	
}
