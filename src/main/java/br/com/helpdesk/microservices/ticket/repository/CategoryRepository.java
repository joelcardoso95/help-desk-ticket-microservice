package br.com.helpdesk.microservices.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helpdesk.microservices.ticket.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
