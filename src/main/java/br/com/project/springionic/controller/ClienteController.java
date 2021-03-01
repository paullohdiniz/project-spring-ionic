package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Cliente;
import br.com.project.springionic.dto.ClienteDTO;
import br.com.project.springionic.services.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> listar(){
        return clienteService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> find(@PathVariable final Integer id){
        Cliente clienteResponse = clienteService.findById(id);
        return ResponseEntity.ok().body(clienteResponse);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Void> salve(@Valid @RequestBody final ClienteDTO cliente){
        Cliente clienteResponse = clienteService.insert(clienteService.fromCliente(cliente));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(clienteResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody final ClienteDTO cliente){
        Cliente clienteResponse = clienteService.update(clienteService.fromCliente(cliente));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Page<ClienteDTO>> listarPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linePerPage", defaultValue = "10") Integer linePerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        Page<ClienteDTO> clientes = clienteService.findAllPerPage(page,linePerPage,orderBy,direction);
        return ResponseEntity.ok().body(clientes);
    }
}
