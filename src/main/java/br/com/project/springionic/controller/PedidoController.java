package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.Pedido;
import br.com.project.springionic.services.CategoriaService;
import br.com.project.springionic.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
