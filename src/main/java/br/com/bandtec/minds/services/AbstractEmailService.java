package br.com.bandtec.minds.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.bandtec.minds.domain.Consulta;
import br.com.bandtec.minds.domain.Psicologo;

public abstract class AbstractEmailService implements EmailService {
	
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Consulta obj) {

		SimpleMailMessage sm = prepareSimpleMailMessageFromConsulta(obj);
		sendEmail(sm);
		
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromConsulta(Consulta obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getPaciente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Consulta obj) {
		Context context = new Context();
		context.setVariable("consulta", obj);
		return templateEngine.process("email/confirmacaoConsulta", context);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Consulta obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromConsulta(obj);
			sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromConsulta(Consulta obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getPaciente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Consulta confirmada! Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}
	@Override
	public void sendNewPasswordEmail(Psicologo psicologo, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(psicologo, newPass);
		sendEmail(sm);
	}

//	criacao do metodo para enviar a  nova senha por email
	protected SimpleMailMessage prepareNewPasswordEmail(Psicologo psicologo, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(psicologo.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
}
