package br.com.bandtec.minds.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.bandtec.minds.domain.TipoConsulta;
import br.com.bandtec.minds.dto.TipoConsultaDTO;
import br.com.bandtec.minds.repositories.TipoConsultaRepository;
import br.com.bandtec.minds.services.exceptions.DataIntegrityException;
import br.com.bandtec.minds.services.exceptions.ObjectNotFoundException;


@Service
public class TipoConsultaService {

	@Autowired
	private TipoConsultaRepository repo;

	public TipoConsulta find(Integer id) {
		Optional<TipoConsulta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + TipoConsulta.class.getName()));
	}
	
	public TipoConsulta insert(TipoConsulta obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public TipoConsulta update(TipoConsulta obj) {
		TipoConsulta newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<TipoConsulta> findAll() {
		return repo.findAll();
	}
	
	public Page<TipoConsulta> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public TipoConsulta fromDTO(TipoConsultaDTO objDto) {
		return new TipoConsulta(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(TipoConsulta newObj, TipoConsulta obj) {
		newObj.setNome(obj.getNome());
	}
}
