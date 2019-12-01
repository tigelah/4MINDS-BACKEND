package br.com.bandtec.minds.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.bandtec.minds.domain.Consulta;
import br.com.bandtec.minds.domain.Psicologo;


public interface EmailService {
	
	void sendOrderConfirmationEmail(Consulta obj);

	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Consulta obj);

	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Psicologo psicologo, String newPass);

}
