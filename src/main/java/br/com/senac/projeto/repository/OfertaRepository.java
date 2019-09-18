package br.com.senac.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.projeto.domain.Oferta;


@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {

}
