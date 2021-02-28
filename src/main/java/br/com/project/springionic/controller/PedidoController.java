package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.Pedido;
import br.com.project.springionic.dto.CategoriaDTO;
import br.com.project.springionic.services.CategoriaService;
import br.com.project.springionic.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listar(){
        return pedidoService.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Pedido> findPedido(@PathVariable final Integer id){
        Pedido pedidoResponse = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedidoResponse);
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
