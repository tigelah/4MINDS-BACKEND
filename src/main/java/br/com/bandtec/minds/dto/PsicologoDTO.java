package br.com.bandtec.minds.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.bandtec.minds.domain.Psicologo;


public class PsicologoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	@JsonProperty
	private String cargo;
	@JsonProperty
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento; 

	private String email;
	
	
	public PsicologoDTO() {
	}

	public PsicologoDTO(Psicologo obj) {
		id = obj.getId();
		nome = obj.getNome();
		cargo = obj.getCargo();
		dataNascimento = obj.getDataNascimento();
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
