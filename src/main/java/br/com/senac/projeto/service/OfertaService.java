package br.com.senac.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.projeto.domain.Oferta;
import br.com.senac.projeto.repository.OfertaRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class OfertaService {
	
	@Autowired
	OfertaRepository repoOferta;
	
	public List<Oferta> buscarTodasOfertas(){
		return repoOferta.findAll();
	}
	
	public Oferta salvar(Oferta oferta) {
		return repoOferta.save(oferta);
	}
		
	public Oferta buscarPorID(Integer id) throws ObjectNotFoundException{
		Optional<Oferta> oferta = repoOferta.findById(id);
		return oferta.orElseThrow(() -> new ObjectNotFoundException("Oferta não encontrada. id:" + id));
	}
	
	public Oferta salvarAlteracao(Oferta ofertaAlterada) throws ObjectNotFoundException{
		Oferta oferta = buscarPorID(ofertaAlterada.getId());
		oferta.setId(ofertaAlterada.getId());
		oferta.setDescricao(ofertaAlterada.getDescricao());
		oferta.setPreco(ofertaAlterada.getPreco());
		oferta.setDataInicio(ofertaAlterada.getDataInicio());
		oferta.setDataFinal(ofertaAlterada.getDataFinal());
		oferta.setStatus(ofertaAlterada.getStatus());
		return salvar(oferta);
	}
	
	
	public void delete(Integer id)  {
		try {
			Oferta oferta =buscarPorID(id);
			oferta.setStatus(false);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Integer id) {
		repoOferta.deleteById(id);
	}
	 
}
