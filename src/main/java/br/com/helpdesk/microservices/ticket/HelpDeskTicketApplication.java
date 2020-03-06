package br.com.helpdesk.microservices.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class HelpDeskTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskTicketApplication.class, args);
	}

}
