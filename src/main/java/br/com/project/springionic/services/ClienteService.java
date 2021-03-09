package br.com.project.springionic.services;

import br.com.project.springionic.controller.domain.Cliente;
import br.com.project.springionic.dto.ClienteDTO;
import br.com.project.springionic.repository.ClienteRepository;
import br.com.project.springionic.services.exception.DataIntegrityException;
import br.com.project.springionic.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        //return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Nao Encontado"));
    }

    public List<ClienteDTO> findAll(){
        List<Cliente> ClienteList = clienteRepository.findAll();

        return ClienteList.stream()
                .map(cliente ->
                        new ClienteDTO(cliente.getId(),cliente.getNome(), cliente.getEmail())).collect(Collectors.toList());
    }
    public Cliente insert(final Cliente cliente){
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    public Cliente update(final Cliente cliente){
        Cliente newCli = findById(cliente.getId());
        udpateData(newCli, cliente);
        return clienteRepository.save(newCli);
    }

    private void udpateData(Cliente newCli, Cliente cliente) {
        newCli.setNome(cliente.getNome());
        newCli.setEmail(cliente.getEmail());
    }

    public void delete(final Integer id){
        ///Tem tratamento de exceçao
        findById(id);
        try{
            clienteRepository.deleteById(id);
        }
        catch (DataIntegrityException ex){
            throw new DataIntegrityException("Nao e possivel excluir Cliente do produto.");
        }

    }
    public Page<ClienteDTO> findAllPerPage(Integer page, Integer linePerPage, String orderBy, String direction){
        Pageable pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest)
                .map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail()));
    }

    public Cliente fromCliente(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(),clienteDTO.getNome(),clienteDTO.getEmail());
    }
}
