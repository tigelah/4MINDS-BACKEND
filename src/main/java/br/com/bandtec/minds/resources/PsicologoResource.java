package br.com.bandtec.minds.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bandtec.minds.domain.Psicologo;
import br.com.bandtec.minds.dto.PsicologoNewDTO;
import br.com.bandtec.minds.services.PsicologoService;

@RestController
@RequestMapping(value = "/psicologos")
public class PsicologoResource {

	@Autowired
	private PsicologoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Psicologo> find(@PathVariable Integer id) {
		Psicologo obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Psicologo>> findAll() {
		List<Psicologo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Psicologo> insert(@Valid @RequestBody PsicologoNewDTO objDto) {
		Psicologo obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
