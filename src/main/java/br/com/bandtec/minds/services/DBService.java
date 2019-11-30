package br.com.bandtec.minds.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.bandtec.minds.domain.Cidade;
import br.com.bandtec.minds.domain.Consulta;
import br.com.bandtec.minds.domain.Endereco;
import br.com.bandtec.minds.domain.Estado;
import br.com.bandtec.minds.domain.ItemSessao;
import br.com.bandtec.minds.domain.Paciente;
import br.com.bandtec.minds.domain.Pagamento;
import br.com.bandtec.minds.domain.PagamentoComBoleto;
import br.com.bandtec.minds.domain.PagamentoComCartao;
import br.com.bandtec.minds.domain.Psicologo;
import br.com.bandtec.minds.domain.TipoConsulta;
import br.com.bandtec.minds.domain.enums.EstadoPagamento;
import br.com.bandtec.minds.domain.enums.Perfil;
import br.com.bandtec.minds.domain.enums.TipoPaciente;
import br.com.bandtec.minds.repositories.CidadeRepository;
import br.com.bandtec.minds.repositories.ConsultaRepository;
import br.com.bandtec.minds.repositories.EnderecoRepository;
import br.com.bandtec.minds.repositories.EstadoRepository;
import br.com.bandtec.minds.repositories.ItemSessaoRepository;
import br.com.bandtec.minds.repositories.PacienteRepository;
import br.com.bandtec.minds.repositories.PagamentoRepository;
import br.com.bandtec.minds.repositories.PsicologoRepository;
import br.com.bandtec.minds.repositories.TipoConsultaRepository;

@Service
public class DBService {

	@Autowired
	private TipoConsultaRepository tipoConsultaRepository;
	@Autowired
	private PsicologoRepository psicologoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ConsultaRepository consultaRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemSessaoRepository itemSessaoRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		TipoConsulta tipoConsulta1 = new TipoConsulta(null, "Psicanálise");
		TipoConsulta tipoConsulta2 = new TipoConsulta(null, "Jungiana");
		TipoConsulta tipoConsulta3 = new TipoConsulta(null, "Lacaniana");
		TipoConsulta tipoConsulta4 = new TipoConsulta(null, "Cognitivo-Construtivista");
		TipoConsulta tipoConsulta5 = new TipoConsulta(null, "Analítico-Comportamental");
		TipoConsulta tipoConsulta6 = new TipoConsulta(null, "Psicodrama");
		TipoConsulta tipoConsulta7 = new TipoConsulta(null, "Gestalt-terapia");

		Psicologo p1 = new Psicologo(null, "Roberto Carlos", "Psicologo", 200.0, sdf.parse("21/03/1957 00:00"),
				"4minds2019@gmail.com", pe.encode("123"));
		Psicologo p2 = new Psicologo(null, "Alberto Carlos", "Psicologo", 250.0, sdf.parse("21/03/1957 00:00"),
				"4minds2020@gmail.com", pe.encode("123"));
		Psicologo p3 = new Psicologo(null, "Lisberto Carlos", "Psicologo", 150.0, sdf.parse("21/03/1957 00:00"),
				"4minds2021@gmail.com", pe.encode("123"));
		Psicologo p4 = new Psicologo(null, "Carlos Antonio", "Psicologo", 120.0, sdf.parse("21/03/1957 00:00"),
				"4minds2022@gmail.com", pe.encode("123"));
		Psicologo p5 = new Psicologo(null, "Elisangela", "Psicologo", 80.0, sdf.parse("21/03/1957 00:00"),
				"4minds2023@gmail.com", pe.encode("123"));
		Psicologo p6 = new Psicologo(null, "Sandra", "Psicologo", 100.0, sdf.parse("21/03/1957 00:00"),
				"4minds2024@gmail.com", pe.encode("123"));
		Psicologo p7 = new Psicologo(null, "Leonardo", "Psicologo", 300.0, sdf.parse("21/03/1957 00:00"),
				"4minds2025@gmail.com", pe.encode("123"));
		Psicologo p8 = new Psicologo(null, "Fernando", "Psicologo", 400.0, sdf.parse("21/03/1957 00:00"),
				"4minds2026@gmail.com", pe.encode("123"));
		Psicologo p9 = new Psicologo(null, "Alyne", "Psicologo", 153.0, sdf.parse("21/03/1957 00:00"),
				"4minds2027@gmail.com", pe.encode("123"));
		Psicologo p10 = new Psicologo(null, "Helena", "Psicologo", 600.5, sdf.parse("21/03/1957 00:00"),
				"4minds2028@gmail.com", pe.encode("123"));
		Psicologo p11 = new Psicologo(null, "Vitoria", "Psicologo", 235.2, sdf.parse("21/03/1957 00:00"),
				"4minds2029@gmail.com", pe.encode("123"));

		tipoConsulta1.getPsicologos().addAll(Arrays.asList(p1, p2, p3));
		tipoConsulta2.getPsicologos().addAll(Arrays.asList(p2));
		tipoConsulta2.getPsicologos().addAll(Arrays.asList(p2, p4));
		tipoConsulta3.getPsicologos().addAll(Arrays.asList(p5, p6));
		tipoConsulta4.getPsicologos().addAll(Arrays.asList(p1, p2, p3, p7));
		tipoConsulta5.getPsicologos().addAll(Arrays.asList(p8));
		tipoConsulta6.getPsicologos().addAll(Arrays.asList(p9, p10));
		tipoConsulta7.getPsicologos().addAll(Arrays.asList(p11));

		p1.getTipoConsulta().addAll(Arrays.asList(tipoConsulta1, tipoConsulta4));
		p1.addPerfil(Perfil.ADMIN);
		p2.getTipoConsulta().addAll(Arrays.asList(tipoConsulta1, tipoConsulta2, tipoConsulta4));
		p2.addPerfil(Perfil.ADMIN);
		p3.getTipoConsulta().addAll(Arrays.asList(tipoConsulta1, tipoConsulta4));
		p3.addPerfil(Perfil.ADMIN);
		p4.getTipoConsulta().addAll(Arrays.asList(tipoConsulta2));
		p4.addPerfil(Perfil.ADMIN);
		p5.getTipoConsulta().addAll(Arrays.asList(tipoConsulta3));
		p5.addPerfil(Perfil.ADMIN);
		p6.getTipoConsulta().addAll(Arrays.asList(tipoConsulta3));
		p6.addPerfil(Perfil.ADMIN);
		p7.getTipoConsulta().addAll(Arrays.asList(tipoConsulta4));
		p7.addPerfil(Perfil.ADMIN);
		p8.getTipoConsulta().addAll(Arrays.asList(tipoConsulta5));
		p8.addPerfil(Perfil.ADMIN);
		p9.getTipoConsulta().addAll(Arrays.asList(tipoConsulta6));
		p9.addPerfil(Perfil.ADMIN);
		p10.getTipoConsulta().addAll(Arrays.asList(tipoConsulta6));
		p10.addPerfil(Perfil.ADMIN);
		p11.getTipoConsulta().addAll(Arrays.asList(tipoConsulta7));
		p11.addPerfil(Perfil.ADMIN);

		tipoConsultaRepository.saveAll(Arrays.asList(tipoConsulta1, tipoConsulta2, tipoConsulta3, tipoConsulta4,
				tipoConsulta5, tipoConsulta6, tipoConsulta7));
		psicologoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Paciente cli1 = new Paciente(null, "Juliana", "peroladaju@gmail.com", "36378912377", TipoPaciente.PESSOAFISICA,
				"Solteiro", "Feminino", sdf.parse("05/11/1991 00:00"));
		Paciente cli2 = new Paciente(null, "Rodrigo", "4minds2019@gmail.com", "36378912377", TipoPaciente.PESSOAFISICA,
				"Solteiro", "Masculino", sdf.parse("21/05/1994 00:00"));

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli2, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		pacienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		Consulta cons1 = new Consulta(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Consulta cons2 = new Consulta(null, sdf.parse("10/10/2017 19:35"), cli2, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, cons1, 6);
		cons1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, cons2, sdf.parse("20/10/2017 00:00"),
				null);
		cons2.setPagamento(pagto2);

		cli1.getConsultas().addAll(Arrays.asList(cons1, cons2));

		consultaRepository.saveAll(Arrays.asList(cons1, cons2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemSessao ip1 = new ItemSessao(cons1, p1, 0.00, 1, 2000.00);
		ItemSessao ip2 = new ItemSessao(cons1, p3, 0.00, 2, 80.00);
		ItemSessao ip3 = new ItemSessao(cons2, p2, 100.00, 1, 800.00);

		cons1.getItens().addAll(Arrays.asList(ip1, ip2));
		cons2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemSessaoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
