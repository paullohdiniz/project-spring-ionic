package br.com.project.springionic.services;

import br.com.project.springionic.controller.domain.Cliente;
import br.com.project.springionic.repository.ClienteRepository;
import br.com.project.springionic.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Nao Encontado"));
    }
    
    public List<Cliente> findAll(){
        List<Cliente> clientList = clienteRepository.findAll();
        return clientList;
    }
}
