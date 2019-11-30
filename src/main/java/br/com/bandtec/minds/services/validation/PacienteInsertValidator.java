package br.com.bandtec.minds.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bandtec.minds.domain.Paciente;
import br.com.bandtec.minds.domain.enums.TipoPaciente;
import br.com.bandtec.minds.dto.PacienteNewDTO;
import br.com.bandtec.minds.repositories.PacienteRepository;
import br.com.bandtec.minds.resources.exception.FieldMessage;
import br.com.bandtec.minds.services.validation.utils.BR;


public class PacienteInsertValidator implements ConstraintValidator<PacienteInsert, PacienteNewDTO> {

	@Autowired
	private PacienteRepository repo;
	
	@Override
	public void initialize(PacienteInsert ann) {
	}

	@Override
	public boolean isValid(PacienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoPaciente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoPaciente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		Paciente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

