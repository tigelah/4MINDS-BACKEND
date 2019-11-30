package br.com.bandtec.minds.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bandtec.minds.services.validation.ConsultaInsert;

@ConsultaInsert
public class ConsultaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataConsulta;
	
//	@NotEmpty(message="Preenchimento obrigatório")
	private String logradouro;

//	@NotEmpty(message="Preenchimento obrigatório")
	private String numero;

	private String complemento;

	private String bairro;

//	@NotEmpty(message="Preenchimento obrigatório")
	private String cep;
	
	private Integer cidadeId;
	

	public ConsultaNewDTO() {
	}


	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}


	public Date getDataConsulta() {
		return dataConsulta;
	}


	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}


	public Integer getCidadeId() {
		return cidadeId;
	}


	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
	
	

}
