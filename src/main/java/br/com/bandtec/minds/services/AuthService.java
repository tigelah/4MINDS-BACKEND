package br.com.bandtec.minds.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.bandtec.minds.domain.Psicologo;
import br.com.bandtec.minds.repositories.PsicologoRepository;
import br.com.bandtec.minds.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private PsicologoRepository psicologoRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Psicologo psicologo = psicologoRepository.findByEmail(email);
		if (psicologo == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		// setando a nova senha ja encriptografada
		psicologo.setPassword(pe.encode(newPass));

		psicologoRepository.save(psicologo);
		emailService.sendNewPasswordEmail(psicologo, newPass);
	}
// criando a nova senha de 10 caracteres que pode ser digito ou letra
	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	
	private char randomChar() {
//		gera um numero de 0 a 2
		int opt = rand.nextInt(3);
		
		if (opt == 0) { // gera um digito
//			 usando a tabela unicode https://unicode-table.com/pt/
//			 gera um numero aleatorio de 0 a 9  por isso rand.nextInt(10) 
//			 e o 48 representa o numero 0 da tabela unicode somado ao numero gerado 
//			consigo ir de 0 a 9 usando a tabela unicode.
			return (char) (rand.nextInt(10) + 48);
		}else if (opt == 1) { // gera letra maiuscula
//			codigo do A maiusculo e 65 e o total de letras possiveis sao 26
			return (char) (rand.nextInt(26) + 65);
		}else { // gera letra minuscula
//			codigo do 'a' minusculo e 97 e o total de letras possiveis sao 26
			return (char) (rand.nextInt(26) + 97);
		}
	}
}