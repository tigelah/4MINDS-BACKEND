package br.com.bandtec.minds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bandtec.minds.domain.TipoConsulta;

@Repository
public interface TipoConsultaRepository extends JpaRepository<TipoConsulta, Integer> {

}
