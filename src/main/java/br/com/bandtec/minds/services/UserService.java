package br.com.bandtec.minds.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.bandtec.minds.security.UserSpringSecurity;

public class UserService {
	
	// retorna o usario logado no sistema
	public static UserSpringSecurity authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}

}
