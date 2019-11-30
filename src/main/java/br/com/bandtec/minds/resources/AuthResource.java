package br.com.bandtec.minds.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bandtec.minds.dto.EmailDTO;
import br.com.bandtec.minds.security.JWTUtil;
import br.com.bandtec.minds.security.UserSpringSecurity;
import br.com.bandtec.minds.services.AuthService;
import br.com.bandtec.minds.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;

	// endpoint para criacao de um novo token
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		
		// pega o usuario logado no sistema
		UserSpringSecurity user = UserService.authenticated();
		// gera um novo token utilizando o usario lugado
		String token = jwtUtil.generateToken(user.getUsername());
		// add na resposta da minha req  o token
		response.addHeader("Authorization", "Bearer " + token);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}