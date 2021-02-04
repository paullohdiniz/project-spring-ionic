package br.com.project.springionic.services;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.Produto;
import br.com.project.springionic.repository.CategoriaRepository;
import br.com.project.springionic.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findById(Integer id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElse(null);
    }

    public List<Produto> findAll(){
        List<Produto> produtoList = produtoRepository.findAll();
        return produtoList;
    }
}
