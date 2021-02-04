package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.Produto;
import br.com.project.springionic.services.CategoriaService;
import br.com.project.springionic.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produto){
        this.produtoService = produto;
    }

    @GetMapping
    public List<Produto> listar(){
        return produtoService.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Produto> findCategoria(@PathVariable final Integer id){
        Produto produtoResponse = produtoService.findById(id);
        return ResponseEntity.ok().body(produtoResponse);
    }
}
