package br.com.senac.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.projeto.domain.Cliente;
import br.com.senac.projeto.repository.ClienteRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService{
    @Autowired
    ClienteRepository repoCliente;

    public List<Cliente>buscarTodosClientes(){
        return repoCliente.findAll();
    }

    public Cliente salvar(Cliente cliente){
        return repoCliente.save(cliente);
    }

    public Cliente buscarPorId(Integer id) throws ObjectNotFoundException{
        Optional<Cliente> cliente = repoCliente.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontra. id:" + id));
    }

    public Cliente alterarCliente(Cliente clienteALterado) throws ObjectNotFoundException{
        Cliente cliente = buscarPorId(clienteALterado.getId());
        cliente.setId(clienteALterado.getId());
        cliente.setNome(clienteALterado.getNome());
        cliente.setSobreNome(clienteALterado.getSobreNome());
        cliente.setEmail(clienteALterado.getEmail());
        cliente.setStatus(clienteALterado.getStatus());
        cliente.setCpf(clienteALterado.getCpf());
        return salvar(cliente);
    }

    public void desativaAtiva(Integer id){
        try {
            Cliente cliente = buscarPorId(id);
            cliente.setStatus(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}