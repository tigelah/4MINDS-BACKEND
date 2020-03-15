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
		Estado est3 = new Estado(null, "Rio de Janeiro");
		Estado est4 = new Estado(null, "Maranhão");
		Estado est6 = new Estado(null, "Bahia");
		Estado est5 = new Estado(null, "Espirito Santo");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est7 = new Estado(null, "Rio Grande do Sul");
		Estado est8 = new Estado(null, "Paraná");
		Estado est9 = new Estado(null, "Mato Grosso");
		Estado est10 = new Estado(null, "Santa Catarina");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Barra do Corda", est4);
		Cidade c5 = new Cidade(null, "Salvador", est6);
		Cidade c6 = new Cidade(null, "Rio de Janeiro", est3);
		Cidade c7 = new Cidade(null, "Porto Alegre", est7);
		Cidade c8 = new Cidade(null, "Vitoria", est5);
		Cidade c9 = new Cidade(null, "Cuiabá", est9);
		Cidade c10 = new Cidade(null, "Florianopolis", est10);
		Cidade c11 = new Cidade(null, "Curitiba", est8);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		est3.getCidades().addAll(Arrays.asList(c6));
		est4.getCidades().addAll(Arrays.asList(c4));
		est5.getCidades().addAll(Arrays.asList(c8));
		est6.getCidades().addAll(Arrays.asList(c5));
		est7.getCidades().addAll(Arrays.asList(c7));
		est8.getCidades().addAll(Arrays.asList(c11));
		est9.getCidades().addAll(Arrays.asList(c9));
		est10.getCidades().addAll(Arrays.asList(c10));

		estadoRepository.saveAll(Arrays.asList(est1, est2,est3,est4,est5,est6,est7,est8,est9,est10));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3,c4,c5,c6,c7,c8,c9,c10,c11));

		Paciente cli1 = new Paciente(null, "Juliana Monteiro", "peroladaju@gmail.com", "36378912377", TipoPaciente.PESSOAFISICA,
				"Solteira", "Feminino", sdf.parse("05/11/1991 00:00"));
		Paciente cli2 = new Paciente(null, "Rodrigo Oliveira", "4minds2019@gmail.com", "41538912377", TipoPaciente.PESSOAFISICA,
				"Solteiro", "Masculino", sdf.parse("21/05/1994 00:00"));
		Paciente cli3 = new Paciente(null, "Jhonny Walker", "jhonnyw@gmail.com", "37897382444", TipoPaciente.PESSOAFISICA,
				"Casado", "Masculino", sdf.parse("25/11/1988 00:00"));
		Paciente cli4 = new Paciente(null, "João Doria", "joaodoria@gmail.com", "38712578309", TipoPaciente.PESSOAFISICA,
				"Casado", "Masculino", sdf.parse("21/03/1957 00:00"));
		Paciente cli5 = new Paciente(null, "Marina Mantega", "marinaman@gmail.com", "05699823130", TipoPaciente.PESSOAFISICA,
				"Casada", "Feminino", sdf.parse("25/12/1980 00:00"));
		Paciente cli6 = new Paciente(null, "Danielle Alves", "daniabs@gmail.com", "21494960036", TipoPaciente.PESSOAFISICA,
				"Solteira", "Feminino", sdf.parse("16/01/1998 00:00"));
		Paciente cli7 = new Paciente(null, "Flavia Sanches", "flavinhaa@hotmail.com", "97341844078", TipoPaciente.PESSOAFISICA,
				"Divorciada", "Feminino", sdf.parse("05/10/1989 00:00"));
		Paciente cli8 = new Paciente(null, "Carlos Caldeira", "carlinhos@gmail.com", "34808470055", TipoPaciente.PESSOAFISICA,
				"Viuvo", "Masculino", sdf.parse("11/05/1950 00:00"));
		Paciente cli9 = new Paciente(null, "Marilia Mendonça", "marimen@gmail.com", "52189333091", TipoPaciente.PESSOAFISICA,
				"Casada", "Feminino", sdf.parse("05/11/1978 00:00"));
		Paciente cli10 = new Paciente(null, "Matheus Lima", "matheus@gmail.com", "54536669000", TipoPaciente.PESSOAFISICA,
				"Solteiro", "Masculino", sdf.parse("09/08/1999 00:00"));
		Paciente cli11 = new Paciente(null, "Ana Bezzera", "aninha@gmail.com", "20809617056", TipoPaciente.PESSOAFISICA,
				"Solteiro", "Feminino", sdf.parse("15/11/2000 00:00"));
		Paciente cli12 = new Paciente(null, "Franscico Cuoco", "4minds2019@gmail.com", "55414422060", TipoPaciente.PESSOAFISICA,
				"Solteiro", "Masculino", sdf.parse("21/05/2002 00:00"));
		Paciente cli13 = new Paciente(null, "Joana Dark", "peroladaju@gmail.com", "36378912377", TipoPaciente.PESSOAFISICA,
				"Solteiro", "Feminino", sdf.parse("23/09/1997 00:00"));


		cli1.getTelefones().addAll(Arrays.asList("27363323", "938383934"));
		cli2.getTelefones().addAll(Arrays.asList("39932434", "938383933"));
		cli3.getTelefones().addAll(Arrays.asList("27363323", "934435456"));
		cli4.getTelefones().addAll(Arrays.asList("27363323", "912323434"));
		cli5.getTelefones().addAll(Arrays.asList("27363323", "943546567"));
		cli6.getTelefones().addAll(Arrays.asList("27363323", "956677678"));
		cli7.getTelefones().addAll(Arrays.asList("27363323", "954353667"));
		cli8.getTelefones().addAll(Arrays.asList("27363323", "933254667"));
		cli9.getTelefones().addAll(Arrays.asList("27363323", "938383934"));
		cli10.getTelefones().addAll(Arrays.asList("27363323", "998095533"));
		cli11.getTelefones().addAll(Arrays.asList("27363323", "970253987"));
		cli12.getTelefones().addAll(Arrays.asList("27363323", "932345678"));
		cli13.getTelefones().addAll(Arrays.asList("27363323", "932344356"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c2);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli2, c2);
		Endereco e3 = new Endereco(null, "Avenida das Lagrimas", "105", "Sala 800", "Centro", "38777012", cli3, c2);
		Endereco e4 = new Endereco(null, "Avenida das Dores", "105", "Sala 800", "Jardim Antartica", "38743546", cli4, c3);
		Endereco e5 = new Endereco(null, "Avenida do Socorro", "105", "Sala 800", "Pq Taipas", "38777324", cli5, c2);
		Endereco e6 = new Endereco(null, "Rua Diogenes Ribeiro", "300", "Apto 303", "Jardim Peri", "38220834", cli6, c2);
		Endereco e7 = new Endereco(null, "Rua Caucaia do Alto", "300", "Apto 303", "Jardim Parana", "38243534", cli7, c2);
		Endereco e8 = new Endereco(null, "Rua Tiburcio de Souza", "300", "Apto 303", "Jardim Damasceno", "38223534", cli8, c2);
		Endereco e9 = new Endereco(null, "Rua Luis Ananias de Souza Leite", "300", "Apto 303", "Jardim Vista Alegre", "382204365", cli9, c2);
		Endereco e10 = new Endereco(null, "Avenida Min Petronio Portela", "105", "Sala 800", "Jardim Paulistano", "387775462", cli10, c3);
		Endereco e11 = new Endereco(null, "Rua Tenente Marques", "300", "Apto 303", "Jardim Guarani", "38220546", cli11, c1);
		Endereco e12 = new Endereco(null, "Rua 25 de Março", "300", "Apto 303", "Jardim Iracema", "382206574", cli12, c11);
		Endereco e13 = new Endereco(null, "Rua Barão de Duprat", "300", "Apto 303", "Brasilandia", "382208546", cli13, c1);
		

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		cli3.getEnderecos().addAll(Arrays.asList(e4));
		cli4.getEnderecos().addAll(Arrays.asList(e5));
		cli5.getEnderecos().addAll(Arrays.asList(e6));
		cli6.getEnderecos().addAll(Arrays.asList(e7));
		cli7.getEnderecos().addAll(Arrays.asList(e8));
		cli8.getEnderecos().addAll(Arrays.asList(e9));
		cli9.getEnderecos().addAll(Arrays.asList(e10));
		cli10.getEnderecos().addAll(Arrays.asList(e11));
		cli11.getEnderecos().addAll(Arrays.asList(e12));
		cli12.getEnderecos().addAll(Arrays.asList(e13));
		cli13.getEnderecos().addAll(Arrays.asList(e1));
		

		pacienteRepository.saveAll(Arrays.asList(cli1, cli2,cli3,cli4,cli5,cli6,cli7,cli8,cli8,cli9,cli10,cli11,cli12,cli13));
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13));

		Consulta cons1 = new Consulta(null, sdf.parse("01/12/2019 10:30"), cli1, e1);
		Consulta cons2 = new Consulta(null, sdf.parse("05/12/2019 11:30"), cli2, e2);
		Consulta cons3 = new Consulta(null, sdf.parse("06/12/2019 12:35"), cli3, e3);
		Consulta cons4 = new Consulta(null, sdf.parse("06/12/2019 13:35"), cli4, e4);
		Consulta cons5 = new Consulta(null, sdf.parse("07/12/2019 14:30"), cli5, e5);
		Consulta cons6 = new Consulta(null, sdf.parse("08/12/2019 15:30"), cli6, e6);
		Consulta cons7 = new Consulta(null, sdf.parse("10/12/2019 16:35"), cli7, e7);
		Consulta cons8 = new Consulta(null, sdf.parse("11/12/2019 17:35"), cli8, e8);
		Consulta cons9 = new Consulta(null, sdf.parse("12/12/2019 18:30"), cli9, e9);
		Consulta cons10 = new Consulta(null, sdf.parse("13/12/2019 20:30"), cli10, e10);
		Consulta cons11 = new Consulta(null, sdf.parse("14/12/2019 21:30"), cli11, e11);
		Consulta cons12 = new Consulta(null, sdf.parse("15/12/2019 10:30"), cli12, e12);
		Consulta cons13 = new Consulta(null, sdf.parse("16/12/2019 11:30"), cli13, e13);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, cons1, 6);
		cons1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, cons2, sdf.parse("20/12/2019 00:00"),
				null);
		cons2.setPagamento(pagto2);
		
		Pagamento pagto3 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, cons3, 6);
		cons3.setPagamento(pagto3);
		Pagamento pagto4 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, cons4, sdf.parse("20/12/2019 00:00"),
				null);
		cons4.setPagamento(pagto4);
		
		Pagamento pagto5 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, cons5, 6);
		cons5.setPagamento(pagto5);
		Pagamento pagto6 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, cons6, sdf.parse("20/12/2019 00:00"),
				null);
		cons6.setPagamento(pagto6);
		
		Pagamento pagto7 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, cons7, 6);
		cons7.setPagamento(pagto7);

		Pagamento pagto8 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, cons8, sdf.parse("20/12/2019 00:00"),
				null);
		cons8.setPagamento(pagto8);
		
		Pagamento pagto9 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, cons9, 6);
		cons9.setPagamento(pagto9);

		Pagamento pagto10 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, cons10, sdf.parse("20/12/2019 00:00"),
				null);
		cons10.setPagamento(pagto10);
		
		Pagamento pagto11 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, cons11, 6);
		cons11.setPagamento(pagto11);

		Pagamento pagto12 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, cons12, sdf.parse("20/12/2019 00:00"),
				null);
		cons12.setPagamento(pagto12);
		
		Pagamento pagto13 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, cons13, sdf.parse("20/12/2019 00:00"),
				null);
		cons13.setPagamento(pagto13);

		cli1.getConsultas().addAll(Arrays.asList(cons1));
		cli2.getConsultas().addAll(Arrays.asList(cons2));
		cli3.getConsultas().addAll(Arrays.asList(cons3));
		cli4.getConsultas().addAll(Arrays.asList(cons4));
		cli5.getConsultas().addAll(Arrays.asList(cons5));
		cli6.getConsultas().addAll(Arrays.asList(cons6));
		cli7.getConsultas().addAll(Arrays.asList(cons7));
		cli8.getConsultas().addAll(Arrays.asList(cons8));
		cli9.getConsultas().addAll(Arrays.asList(cons9));
		cli10.getConsultas().addAll(Arrays.asList(cons10));
		cli11.getConsultas().addAll(Arrays.asList(cons11));
		cli12.getConsultas().addAll(Arrays.asList(cons12));
		cli13.getConsultas().addAll(Arrays.asList(cons13));

		consultaRepository.saveAll(Arrays.asList(cons1, cons2,cons3,cons4,cons5,cons6,cons7,cons8,cons9,cons10,cons11,cons12,cons13));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2,pagto3,pagto4,pagto5,pagto6,pagto7,pagto8,pagto9,pagto10,pagto11,pagto12,pagto13));

		ItemSessao ip1 = new ItemSessao(cons1, p1, 0.00, 1, 2000.00);
		ItemSessao ip2 = new ItemSessao(cons2, p1, 100.00, 1, 800.00);
		ItemSessao ip3 = new ItemSessao(cons3, p1, 10.00, 2, 180.00);
		ItemSessao ip4 = new ItemSessao(cons4, p1, 40.00, 2, 230.00);
		ItemSessao ip5 = new ItemSessao(cons5, p1, 60.00, 2, 480.00);
		ItemSessao ip6 = new ItemSessao(cons6, p1, 70.00, 2, 510.00);
		ItemSessao ip7 = new ItemSessao(cons7, p1, 0.00, 2, 200.00);
		ItemSessao ip8 = new ItemSessao(cons8, p1, 0.00, 2, 280.00);
		ItemSessao ip9 = new ItemSessao(cons9, p1, 0.00, 2, 2280.00);
		ItemSessao ip10 = new ItemSessao(cons10, p1, 10.00, 2, 380.00);
		ItemSessao ip11 = new ItemSessao(cons11, p1, 20.00, 2, 480.00);
		ItemSessao ip12 = new ItemSessao(cons12, p1, 40.00, 2, 580.00);
		ItemSessao ip13 = new ItemSessao(cons13, p1, 0.00, 2, 180.00);
		

		cons1.getItens().addAll(Arrays.asList(ip1));
		cons2.getItens().addAll(Arrays.asList(ip2));
		cons3.getItens().addAll(Arrays.asList(ip3));
		cons4.getItens().addAll(Arrays.asList(ip4));
		cons5.getItens().addAll(Arrays.asList(ip5));
		cons6.getItens().addAll(Arrays.asList(ip6));
		cons7.getItens().addAll(Arrays.asList(ip7));
		cons8.getItens().addAll(Arrays.asList(ip8));
		cons9.getItens().addAll(Arrays.asList(ip9));
		cons10.getItens().addAll(Arrays.asList(ip10));
		cons11.getItens().addAll(Arrays.asList(ip11));
		cons12.getItens().addAll(Arrays.asList(ip12));
		cons13.getItens().addAll(Arrays.asList(ip13));

		p1.getItens().addAll(Arrays.asList(ip1,ip2,ip3,ip4,ip5,ip6,ip7,ip8,ip9,ip10,ip11,ip12,ip13));

		itemSessaoRepository.saveAll(Arrays.asList(ip1,ip2,ip3,ip4,ip5,ip6,ip7,ip8,ip9,ip10,ip11,ip12,ip13));

	}

}
