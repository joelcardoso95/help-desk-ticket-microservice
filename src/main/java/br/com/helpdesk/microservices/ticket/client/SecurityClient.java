package br.com.helpdesk.microservices.ticket.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.helpdesk.microservices.ticket.dto.UserDTO;

@FeignClient("security")
public interface SecurityClient {
	
	@GetMapping("/users/loadLoggedUser")
	public UserDTO loadUserInfo();

}
