package br.com.senac.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.projeto.domain.Cliente;
import br.com.senac.projeto.service.ClienteService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/cliente")
public class ClienteController{

    @Autowired
    ClienteService clienteService;
    @GetMapping("/listar")
    public ModelAndView listaTodosClientes(){
        ModelAndView mv = new ModelAndView("cliente/paginaListaCliente");
        mv.addObject("clientes", clienteService.buscarTodosClientes());
        return mv;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrarCliente(){
        ModelAndView mv = new ModelAndView("cliente/cadastrarCliente");
        mv.addObject("cliente", new Cliente());
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvarCliente(Cliente cliente){
        clienteService.salvar(cliente);
        return new ModelAndView("redirect:/cliente/listar");
    }
    @GetMapping("/alterar/{id}")
    public ModelAndView alterarCliente(@PathVariable("id") Integer id) throws ObjectNotFoundException{
        ModelAndView mv = new ModelAndView("cliente/alterarCliente");
        mv.addObject("cliente", clienteService.buscarPorId(id));
        return mv;
    }
    @PostMapping("/alterar")
    public ModelAndView alterarCliente(Cliente cliente) throws ObjectNotFoundException{
        clienteService.alterarCliente(cliente);
        return new ModelAndView("redirect:/cliente/listar");
    }

    @GetMapping("/desativarAtivar/{id}")
    public ModelAndView desativarAtivar(@PathVariable("id") Integer id){
        clienteService.desativaAtiva(id);
        return new ModelAndView("redirect:/cliente/listar");
    }
}