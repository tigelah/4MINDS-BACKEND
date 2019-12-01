package br.com.bandtec.minds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bandtec.minds.domain.Paciente;



@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

	@Transactional(readOnly=true)
	Paciente findByEmail(String email);
}
