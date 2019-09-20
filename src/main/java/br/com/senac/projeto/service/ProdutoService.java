package br.com.senac.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.projeto.domain.Produto;
import br.com.senac.projeto.repository.ProdutoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repoProduto;
	
	public List<Produto> buscarTodosProdutos() {
		return repoProduto.findAll();
	}
	
	public Produto salvar(Produto produto) {
		return repoProduto.save(produto);
	}
	
	public Produto buscarPorID(Integer id) throws ObjectNotFoundException{
		Optional<Produto> produto = repoProduto.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado. id" + id));
	}
	
	public Produto salvarAlteracao(Produto produtoAlterado) throws ObjectNotFoundException{
		Produto produto = buscarPorID(produtoAlterado.getId());
		produto.setId(produtoAlterado.getId());
		produto.setNome(produtoAlterado.getNome());
		produto.setDescricao(produtoAlterado.getDescricao());
		produto.setStatus(produtoAlterado.getStatus());
		return salvar(produto);
	}
	
	public void desativaAtiva(Integer id) {
		try {
			Produto produto = buscarPorID(id);
			produto.setStatus(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
