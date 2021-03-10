package br.com.project.springionic.services;

import br.com.project.springionic.controller.domain.*;
import br.com.project.springionic.dto.ClienteDTO;
import br.com.project.springionic.dto.ClienteNewDTO;
import br.com.project.springionic.repository.ClienteRepository;
import br.com.project.springionic.repository.EnderecoRepository;
import br.com.project.springionic.services.exception.DataIntegrityException;
import br.com.project.springionic.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

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

    public Cliente insert(Cliente cliente){
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente update(Cliente cliente){
        Cliente newObj = findById(cliente.getId());
        udpateData(newObj, cliente);
        return clienteRepository.save(newObj);
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

    public Cliente fromDTONewClientes(ClienteNewDTO objDto) {
        //TODO Ver se precisar instancia cidadeRepository
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoClienteEnum.PESSOA_FISICA, pe.encode(objDto.getSenha()));
        Estado estado = new Estado(null, "SP", null);
        Cidade cid = new Cidade(objDto.getCidadeId(), "São Paulo", estado);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!=null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3()!=null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    public Cliente fromCliente(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(),clienteDTO.getNome(),clienteDTO.getEmail());
    }
}
