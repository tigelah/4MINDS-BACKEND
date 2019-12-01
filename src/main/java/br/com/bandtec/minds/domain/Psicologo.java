	package br.com.bandtec.minds.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.bandtec.minds.domain.enums.Perfil;

@Entity
public class Psicologo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@JsonProperty
	private String nome;
	@JsonProperty
	private String cargo;
	@JsonProperty
	private Double preco;
	@JsonProperty
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento; 

	private String email;
	
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PSICOLOGO_TIPO",
		joinColumns = @JoinColumn(name = "psicolgo_id"),
		inverseJoinColumns = @JoinColumn(name = "tipo_id")
	)
	
	private List<TipoConsulta> tipoConsultas = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="id.psicologo")
	private Set<ItemSessao> itens = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	
	public Psicologo() {
		addPerfil(Perfil.ADMIN);
	}



	public Psicologo(Integer id, String nome, String cargo, Double preco, Date dataNascimento, String email,
			String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
		this.preco = preco;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.password = password;
		addPerfil(Perfil.ADMIN);
	}



	@JsonIgnore
	public List<Consulta> getConsultas() {
		List<Consulta> lista = new ArrayList<>();
		for (ItemSessao x : itens) {
			lista.add(x.getConsulta());
		}
		return lista;
	}
	
	public Set<Perfil> getPerfis() {
//		percore a colecao de numeros inteiros e converte para o tipo enumerado perfil
//		para cada elemento X dessa colecao  e invocado o metodo toEnum passando X como argumento
//		e pra finalizar converte tudo pra colecao 
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
//  implementando o metodo para adicionar o perfil
	public void addPerfil(Perfil perfil) {
//		 adicionando o perfil pegando pelo cod.
		perfis.add(perfil.getCod());
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<TipoConsulta> getTipoConsulta() {
		return tipoConsultas;
	}

	public void setCategorias(List<TipoConsulta> tipoConsultas) {
		this.tipoConsultas = tipoConsultas;
	}

	public Set<ItemSessao> getItens() {
		return itens;
	}

	public void setItens(Set<ItemSessao> itens) {
		this.itens = itens;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Psicologo other = (Psicologo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	

}
