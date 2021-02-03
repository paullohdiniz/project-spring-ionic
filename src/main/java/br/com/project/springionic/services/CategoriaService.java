package br.com.project.springionic.services;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria busca(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElse(null);
    }
}
