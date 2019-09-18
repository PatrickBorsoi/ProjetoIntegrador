package br.com.senac.projeto.inicializacao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.projeto.domain.Oferta;
import br.com.senac.projeto.service.OfertaService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	OfertaService ofertaService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Oferta oferta1 = new Oferta();
		oferta1.setDescricao("Estou conseguindo");
		oferta1.setStatus(true);
		oferta1.setDataFinal(LocalDate.now());
		ofertaService.salvar(oferta1);
		
		List<Oferta> listaOferta = ofertaService.buscarTodasOfertas();
		
		for(Oferta oferta:listaOferta) {
				System.out.println(oferta.getDescricao());
		}
	} 
}
