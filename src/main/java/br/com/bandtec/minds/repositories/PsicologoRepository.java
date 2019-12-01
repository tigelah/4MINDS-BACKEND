package br.com.bandtec.minds.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bandtec.minds.domain.Psicologo;
import br.com.bandtec.minds.domain.TipoConsulta;

@Repository
public interface PsicologoRepository extends JpaRepository<Psicologo, Integer> {

	@Transactional(readOnly = true)
	Psicologo findByEmail(String email);

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Psicologo obj INNER JOIN obj.tipoConsultas cat WHERE obj.nome LIKE %:nome% AND cat IN :tipoConsultas")
	Page<Psicologo> findDistinctByNomeContainingAndTipoConsultaIn(@Param("nome") String nome,
			@Param("tipoConsultas") List<TipoConsulta> tipoConsultas, Pageable pageRequest);
}
