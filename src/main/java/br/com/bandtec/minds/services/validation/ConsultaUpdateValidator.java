package br.com.bandtec.minds.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.bandtec.minds.domain.Consulta;
import br.com.bandtec.minds.dto.ConsultaDTO;
import br.com.bandtec.minds.repositories.ConsultaRepository;
import br.com.bandtec.minds.resources.exception.FieldMessage;

public class ConsultaUpdateValidator implements ConstraintValidator<ConsultaUpdate, ConsultaDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ConsultaRepository repo;
	
	@Override
	public void initialize(ConsultaUpdate ann) {
	}

	@Override
	public boolean isValid(ConsultaDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Consulta aux = repo.findByDataConsulta(objDto.getDataConsulta());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("dataConsulta", "Data da Consulta já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
