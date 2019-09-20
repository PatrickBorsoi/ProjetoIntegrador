package br.com.senac.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.projeto.domain.Oferta;
import br.com.senac.projeto.domain.Produto;
import br.com.senac.projeto.service.OfertaService;
import br.com.senac.projeto.service.ProdutoService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/oferta")
public class OfertaController {
	
	@Autowired
	OfertaService ofertaService;
	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping("/listar")
	public ModelAndView listaTodasOfertas() {
		ModelAndView mv = new ModelAndView("oferta/paginaListaOfertas");
		mv.addObject("ofertas", ofertaService.buscarTodasOfertas());
		
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarOferta() {
		ModelAndView mv = new ModelAndView("oferta/cadastraOferta");
		mv.addObject("oferta", new Oferta());
		mv.addObject("produtos", produtoService.buscarTodosProdutos());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarOferta(Oferta oferta) {
		ofertaService.salvar(oferta);
		return new ModelAndView("redirect:/oferta/listar");
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarOferta(@PathVariable("id") Integer idOferta) throws ObjectNotFoundException{
		ModelAndView mv = new ModelAndView("oferta/alterarOferta");
		mv.addObject("oferta", ofertaService.buscarPorID(idOferta));
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterarOferta(Oferta ofertaAlterada) throws ObjectNotFoundException{
		ofertaService.salvarAlteracao(ofertaAlterada);
		return new ModelAndView("redirect:/oferta/listar");
	}
	
	@GetMapping("/deletar/{id}")
	public ModelAndView deletarOferta(@PathVariable("id") Integer id) {
		ofertaService.delete(id);
		return new ModelAndView("redirect:/oferta/listar");
	}
} 
