package br.com.bandtec.minds.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataConsulta;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "consulta")
	private Pagamento pagamento;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;


	
	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco enderecoDeConsulta;

	@OneToMany(mappedBy = "id.consulta")
	private Set<ItemSessao> itens = new HashSet<>();

	public Consulta() {
	}

	public Consulta(Integer id, Date dataConsulta, Paciente paciente, Endereco enderecoDeConsulta) {
		super();
		this.id = id;
		this.dataConsulta = dataConsulta;
		this.paciente = paciente;
		this.enderecoDeConsulta = enderecoDeConsulta;
	}

	public double getValorTotal() {
		double soma = 0.0;
		for (ItemSessao ip : itens) {
			soma = soma + ip.getSubTotal();
		}
		return soma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Endereco getEnderecoDeConsulta() {
		return enderecoDeConsulta;
	}

	public void setEnderecoDeConsulta(Endereco enderecoDeConsulta) {
		this.enderecoDeConsulta = enderecoDeConsulta;
	}

	public Set<ItemSessao> getItens() {
		return itens;
	}

	public void setItens(Set<ItemSessao> itens) {
		this.itens = itens;
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
		Consulta other = (Consulta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append("\nData Hora: ");
		builder.append(sdf.format(getDataConsulta()));
		builder.append("\nPaciente: ");
		builder.append(getPaciente().getNome());

		builder.append("\nSituação do pagamento: ");
		builder.append(getPagamento().getEstado().getDescricao());
		builder.append("\nDetalhes:\n");
		for (ItemSessao ip : getItens()) {
			builder.append(ip.toString());
		}
		builder.append("Valor total: ");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();

	}
}