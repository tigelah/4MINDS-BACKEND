package br.com.bandtec.minds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.bandtec.minds.domain.Psicologo;
import br.com.bandtec.minds.repositories.PsicologoRepository;
import br.com.bandtec.minds.security.UserSpringSecurity;

@Service
public class UserDetalilServiceImp  implements UserDetailsService {

	@Autowired
	private PsicologoRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Psicologo psicologo = repo.findByEmail(email);
		if (psicologo == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(psicologo.getId(), psicologo.getEmail(), psicologo.getPassword(), psicologo.getPerfis());
	}
}
