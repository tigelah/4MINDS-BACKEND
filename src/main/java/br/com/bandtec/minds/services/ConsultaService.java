package br.com.bandtec.minds.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.bandtec.minds.domain.Cidade;
import br.com.bandtec.minds.domain.Consulta;
import br.com.bandtec.minds.domain.Endereco;
import br.com.bandtec.minds.domain.ItemSessao;
import br.com.bandtec.minds.domain.PagamentoComBoleto;
import br.com.bandtec.minds.domain.enums.EstadoPagamento;
import br.com.bandtec.minds.dto.ConsultaDTO;
import br.com.bandtec.minds.dto.ConsultaNewDTO;
import br.com.bandtec.minds.repositories.ConsultaRepository;
import br.com.bandtec.minds.repositories.ItemSessaoRepository;
import br.com.bandtec.minds.repositories.PagamentoRepository;
import br.com.bandtec.minds.services.exceptions.DataIntegrityException;
import br.com.bandtec.minds.services.exceptions.ObjectNotFoundException;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private PsicologoService psicologoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemSessaoRepository itemSessaoRepository;
	
	@Autowired
	private EmailService emailService;

	public Consulta find(Integer id) {
		Optional<Consulta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Consulta.class.getName()));
	}

	@Transactional
	public Consulta insert(Consulta obj) {
		obj.setId(null);
		obj.setDataConsulta(new Date());
		obj.setPaciente(pacienteService.buscar(obj.getPaciente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getDataConsulta());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemSessao itemSessao : obj.getItens()) {
			itemSessao.setDesconto(0.0);
			itemSessao.setPsicologo(psicologoService.find(itemSessao.getPsicologo().getId()));
			itemSessao.setPreco(itemSessao.getPsicologo().getPreco());
			itemSessao.setConsulta(obj);
		}
		itemSessaoRepository.saveAll(obj.getItens());
		
//		emailService.sendOrderConfirmationEmail(obj); // envio de email so no texto
		emailService.sendOrderConfirmationHtmlEmail(obj); // envio de  email em HTML com thymeleaf
//		System.out.println(obj);
		return obj;
		
	}

	public Consulta update(Consulta obj) {
		Consulta newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj) ;
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há psicologos relacionados");
		}
	}

	public List<Consulta> findAll() {
		return repo.findAll();
	}

	public Page<Consulta> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Consulta fromDTO(ConsultaDTO objDto) {
		return new Consulta(objDto.getId(), objDto.getDataConsulta(), null, null);
	}

	public Consulta fromDTO(ConsultaNewDTO objDto) {
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), null, cid);
		Consulta cons = new Consulta(null, objDto.getDataConsulta(), null, end);

		return cons;
	}

	private void updateData(Consulta newObj, Consulta obj) {
		newObj.setDataConsulta(obj.getDataConsulta());
		newObj.setEnderecoDeConsulta(obj.getEnderecoDeConsulta());
	}
}
