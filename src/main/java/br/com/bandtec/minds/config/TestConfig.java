package br.com.bandtec.minds.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.bandtec.minds.services.DBService;
import br.com.bandtec.minds.services.EmailService;
import br.com.bandtec.minds.services.MockEmailService;
import br.com.bandtec.minds.services.SmtpEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

//	@Bean
//	public boolean instantiateDatabase() throws ParseException {
//		dbService.instantiateTestDatabase();
//		return true;
//	}

//	@Bean
//	public EmailService emailService() {
//		return new MockEmailService();
//	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}