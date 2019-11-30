package br.com.bandtec.minds.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bandtec.minds.domain.Consulta;
import br.com.bandtec.minds.dto.ConsultaNewDTO;
import br.com.bandtec.minds.repositories.ConsultaRepository;
import br.com.bandtec.minds.resources.exception.FieldMessage;


public class ConsultaInsertValidator implements ConstraintValidator<ConsultaInsert, ConsultaNewDTO> {

	@Autowired
	private ConsultaRepository repo;
	
	@Override
	public void initialize(ConsultaInsert ann) {
	}

	@Override
	public boolean isValid(ConsultaNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getLogradouro().equals( new Consulta().getEnderecoDeConsulta().getLogradouro())) {
			list.add(new FieldMessage("logradouro", "Endereço inválido"));
		}


		Consulta aux = repo.findByDataConsulta(objDto.getDataConsulta());
		if (aux != null) {
			list.add(new FieldMessage("dataConsulta", "Data já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
