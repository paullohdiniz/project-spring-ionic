package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.Produto;
import br.com.project.springionic.controller.utils.URL;
import br.com.project.springionic.dto.ProdutoDTO;
import br.com.project.springionic.services.CategoriaService;
import br.com.project.springionic.services.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<Produto> findProduto(@PathVariable final Integer id){
        Produto produtoResponse = produtoService.findById(id);
        return ResponseEntity.ok().body(produtoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value="nome", defaultValue="") String nome,
            @RequestParam(value="categorias", defaultValue="") String categorias,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj.getId(), obj.getNome(), obj.getPreco()));
        return ResponseEntity.ok().body(listDto);
    }
}
