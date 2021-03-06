package br.com.helpdesk.microservices.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@SpringBootApplication
@EnableResourceServer
@EnableFeignClients
public class HelpDeskTicketApplication {
	
	@Bean
	public RequestInterceptor getAuthenticationInterceptor() {
		return new RequestInterceptor() {
			
			@Override
			public void apply(RequestTemplate template) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null) {
					return;
				}
				
				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
				template.header("Authorization", "Bearer " + details.getTokenValue());	
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskTicketApplication.class, args);
	}
}
