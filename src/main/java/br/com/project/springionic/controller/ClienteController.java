package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Cliente;
import br.com.project.springionic.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listar(){
        return clienteService.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Cliente> findCategoria(@PathVariable final Integer id){
        Cliente clienteResponse = clienteService.findById(id);
        return ResponseEntity.ok().body(clienteResponse);
    }
}
