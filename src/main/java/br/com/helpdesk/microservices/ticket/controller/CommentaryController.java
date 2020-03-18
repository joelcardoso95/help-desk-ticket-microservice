package br.com.helpdesk.microservices.ticket.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helpdesk.microservices.ticket.dto.CommentaryDTO;
import br.com.helpdesk.microservices.ticket.model.Commentary;
import br.com.helpdesk.microservices.ticket.service.CommentaryService;

@RestController
@RequestMapping("/commentary")
public class CommentaryController {
	
	@Autowired
	private CommentaryService commentaryService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createCommentary(@Valid @RequestBody CommentaryDTO commentaryDTO) {
		Commentary commentary = commentaryService.fromDTO(commentaryDTO);
		commentary = commentaryService.createCommentary(commentary);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(commentary.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Commentary>> findAll() {
		List<Commentary> commentaries = commentaryService.findAll();
		return ResponseEntity.ok().body(commentaries);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCommentary(@PathVariable Long id) throws Exception {
		commentaryService.deleteCommentary(id);
		return ResponseEntity.noContent().build();
	}
}
