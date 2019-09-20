package br.com.senac.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.projeto.domain.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
}
