package br.com.bandtec.minds.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.bandtec.minds.domain.TipoConsulta;


public class TipoConsultaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
//	@NotEmpty(message="Preenchimento obrigatório")
//	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	public TipoConsultaDTO() {
	}
	
	public TipoConsultaDTO(TipoConsulta obj) {
		id = obj.getId();
		nome = obj.getNome();
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
}
