package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.dto.CategoriaDTO;
import br.com.project.springionic.services.CategoriaService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoria){
        this.categoriaService = categoria;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar(){
        List<CategoriaDTO> categorias = categoriaService.findAll();
        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> find(@PathVariable final Integer id){
        Categoria categoriaResponse = categoriaService.findById(id);
        return ResponseEntity.ok().body(categoriaResponse);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Void> salve(@Valid @RequestBody final CategoriaDTO categoria){
        Categoria categoriaResponse = categoriaService.insert(categoriaService.fromCategoria(categoria));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categoriaResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody final CategoriaDTO categoria){
        Categoria categoriaResponse = categoriaService.update(categoriaService.fromCategoria(categoria));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Page<CategoriaDTO>> listarPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linePerPage", defaultValue = "10") Integer linePerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        Page<CategoriaDTO> categorias = categoriaService.findAllPerPage(page,linePerPage,orderBy,direction);
        return ResponseEntity.ok().body(categorias);
    }
}
