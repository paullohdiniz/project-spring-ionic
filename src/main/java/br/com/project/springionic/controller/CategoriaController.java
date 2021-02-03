package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @GetMapping
    public List<Categoria> listar(){
        List<Categoria> categorias = new ArrayList<>();

        Categoria categoria = new Categoria(1,"Informatica");
        Categoria categoria2 = new Categoria(2,"Financeiro");
        categorias.add(categoria);
        categorias.add(categoria2);
        return categorias;
    }
}
