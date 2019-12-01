package br.com.bandtec.minds.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bandtec.minds.domain.Psicologo;
import br.com.bandtec.minds.dto.PsicologoDTO;
import br.com.bandtec.minds.dto.PsicologoNewDTO;
import br.com.bandtec.minds.repositories.PsicologoRepository;
import br.com.bandtec.minds.services.exceptions.ObjectNotFoundException;

@Service
public class PsicologoService {

	@Autowired
	private PsicologoRepository repo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Psicologo find(Integer id) {
		Optional<Psicologo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Psicologo.class.getName()));
	}

	public Psicologo fromDTO(PsicologoDTO objDto) {
		return new Psicologo(objDto.getId(), objDto.getNome(), objDto.getCargo(), null, objDto.getDataNascimento(),
				objDto.getEmail(), null);
	}

	public Psicologo fromDTO(PsicologoNewDTO objDto) {
		Psicologo psicologo = new Psicologo(objDto.getId(), objDto.getNome(), objDto.getCargo(), null,
				objDto.getDataNascimento(), objDto.getEmail(), passwordEncoder.encode(objDto.getPassword()));
		return psicologo;
	}
	public List<Psicologo> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Psicologo insert(Psicologo obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

}
