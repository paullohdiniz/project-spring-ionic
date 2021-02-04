package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.services.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoria){
        this.categoriaService = categoria;
    }

    @GetMapping
    public List<Categoria> listar(){
        return categoriaService.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Categoria> findCategoria(@PathVariable final Integer id){
        Categoria categoriaResponse = categoriaService.findById(id);
        return ResponseEntity.ok().body(categoriaResponse);
    }
}
