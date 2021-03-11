package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.Pedido;
import br.com.project.springionic.dto.CategoriaDTO;
import br.com.project.springionic.services.CategoriaService;
import br.com.project.springionic.services.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

//    @GetMapping
//    public List<Pedido> listar(){
//        return pedidoService.findAll();
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findPedido(@PathVariable final Integer id){
        Pedido pedidoResponse = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedidoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<Pedido>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="instante") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<Pedido> list = pedidoService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
        obj = pedidoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Void> update(@Valid @RequestBody final CategoriaDTO categoria){
//        Categoria categoriaResponse = pedidoService.update(pedidoService.fromCategoria(categoria));
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> delete(@PathVariable final Integer id){
//        pedidoService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
