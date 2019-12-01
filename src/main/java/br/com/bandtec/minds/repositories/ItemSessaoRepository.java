package br.com.bandtec.minds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bandtec.minds.domain.ItemSessao;


@Repository
public interface ItemSessaoRepository extends JpaRepository<ItemSessao, Integer> {

}
