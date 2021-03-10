package br.com.project.springionic.services;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.Produto;
import br.com.project.springionic.repository.CategoriaRepository;
import br.com.project.springionic.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto findById(Integer id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElse(null);
    }

    public List<Produto> findAll(){
        List<Produto> produtoList = produtoRepository.findAll();
        return produtoList;
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }

}
