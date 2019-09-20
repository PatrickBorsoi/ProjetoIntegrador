package br.com.senac.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.projeto.domain.Produto;
import br.com.senac.projeto.service.ProdutoService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@GetMapping("/listar")
	public ModelAndView listaTodosProdutos() {
		ModelAndView mv = new ModelAndView("produto/paginaListarProduto");
		mv.addObject("produtos", produtoService.buscarTodosProdutos());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastraProduto() {
		ModelAndView mv = new ModelAndView("produto/cadastraProduto");
		mv.addObject("produto", new Produto());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarProduto(Produto produto) {
		produtoService.salvar(produto);
		return new ModelAndView("redirect:/produto/listar");
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarProduto(@PathVariable("id") Integer idProduto) throws ObjectNotFoundException{
		ModelAndView mv = new ModelAndView("produto/alterarProduto");
		mv.addObject("produto", produtoService.buscarPorID(idProduto));
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterarProduto(Produto alterarProduto) throws ObjectNotFoundException{
		produtoService.salvarAlteracao(alterarProduto);
		return new ModelAndView("redirect:/produto/listar");
	}
	
	@GetMapping("/desativaAtiva/{id}")
	public ModelAndView desativaAtiva(@PathVariable("id") Integer id) {
		produtoService.desativaAtiva(id);
		return new ModelAndView("redirect:/produto/listar");
	}
	
}
