package fhict.nl.infralabauthenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class InfralabAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfralabAuthenticationServiceApplication.class, args);
	}

}
