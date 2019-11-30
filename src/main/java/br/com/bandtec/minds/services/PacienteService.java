package br.com.bandtec.minds.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bandtec.minds.domain.Cidade;
import br.com.bandtec.minds.domain.Endereco;
import br.com.bandtec.minds.domain.Paciente;
import br.com.bandtec.minds.domain.enums.Perfil;
import br.com.bandtec.minds.domain.enums.TipoPaciente;
import br.com.bandtec.minds.dto.PacienteDTO;
import br.com.bandtec.minds.dto.PacienteNewDTO;
import br.com.bandtec.minds.repositories.EnderecoRepository;
import br.com.bandtec.minds.repositories.PacienteRepository;
import br.com.bandtec.minds.security.UserSpringSecurity;
import br.com.bandtec.minds.services.exceptions.AuthorizationException;
import br.com.bandtec.minds.services.exceptions.DataIntegrityException;
import br.com.bandtec.minds.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Paciente buscar(Integer id) {
		
		UserSpringSecurity user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Paciente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Paciente.class.getName()));
	}

	@Transactional
	public Paciente insert(Paciente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Paciente update(Paciente obj) {
		Paciente newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return repo.saveAndFlush(newObj);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}

	public List<Paciente> findAll() {
		return repo.findAll();
	}

	public Page<Paciente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Paciente fromDTO(PacienteDTO objDto) {
		return new Paciente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null,TipoPaciente.toEnum(objDto.getTipo()), objDto.getEstadoCivil(),
				null, null);
	}

	public Paciente fromDTO(PacienteNewDTO objDto) {
		Paciente paciente = new Paciente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoPaciente.toEnum(objDto.getTipo()), objDto.getEstadoCivil(), objDto.getSexo(),
				objDto.getDataNascimento());
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), paciente, cid);
		paciente.getEnderecos().add(end);
		paciente.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			paciente.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			paciente.getTelefones().add(objDto.getTelefone3());
		}
		return paciente;
	}

	private void updateData(Paciente newObj, Paciente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
