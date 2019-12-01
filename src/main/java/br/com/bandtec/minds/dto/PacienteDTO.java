package br.com.bandtec.minds.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.bandtec.minds.domain.Paciente;
import br.com.bandtec.minds.services.validation.PacienteUpdate;


@PacienteUpdate
public class PacienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
//	@NotEmpty(message="Preenchimento obrigatório")
//	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	

//	@Email(message="Email inválido")
	private String email;
	
	
//	@NotEmpty(message="Preenchimento obrigatório")
//	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String estadoCivil;
	
	private Integer tipo;
	
	public PacienteDTO() {
	}

	public PacienteDTO(Paciente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
}
