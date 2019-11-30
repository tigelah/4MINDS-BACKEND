package br.com.bandtec.minds.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bandtec.minds.domain.Consulta;



@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

	@Transactional(readOnly=true)
	Consulta findByDataConsulta(Date data);
}
 