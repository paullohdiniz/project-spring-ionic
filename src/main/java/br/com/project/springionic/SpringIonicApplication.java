package br.com.project.springionic;

import br.com.project.springionic.controller.domain.*;
import br.com.project.springionic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoCategoriaRepository produtoCategoriaRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringIonicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat01 = new Categoria(null, "Informatica",null);
        Categoria cat02 = new Categoria(null, "Financeiro", null);

        Produto produto01 = new Produto(null, "Computador", 3.8, null);
        Produto produto02 = new Produto(null, "Impressora", 3.8, null);
        Produto produto03 = new Produto(null, "Mouse", 3.8, null);
        produtoRepository.saveAll(Arrays.asList(produto01,produto02,produto03));

        //        Set<Produto> produtos = new HashSet<>();
//        produtos.add(produto01);
//        produtos.add(produto02);
//        produtos.add(produto03);


        //cat01.getProdutosCategoria().add(produtos);
        categoriaRepository.saveAll(Arrays.asList(cat01,cat02));

        ProdutoCategoria produtoCategoria = new ProdutoCategoria();
        produtoCategoria.setCategoria(cat01);
        produtoCategoria.setProduto(produto01);
        produtoCategoriaRepository.save(produtoCategoria);

        ProdutoCategoria produtoCategoria02 = new ProdutoCategoria();
        produtoCategoria.setCategoria(cat02);
        produtoCategoria.setProduto(produto02);
        produtoCategoriaRepository.save(produtoCategoria02);

        Estado est01 = new Estado(null, "Minas Gerais", null);
        Estado est02 = new Estado(null, "Sao Paulo", null);

        Cidade cid01 = new Cidade(null, "Uberlandia", est01);
        Cidade cid02 = new Cidade(null, "Sao Paulo", est02);
        Cidade cid03 = new Cidade(null, "Campinas", est02);

        est01.getCidades().addAll(Arrays.asList(cid01));
        est02.getCidades().addAll(Arrays.asList(cid02,cid03));

        estadoRepository.saveAll(Arrays.asList(est01,est02));
        cidadeRepository.saveAll(Arrays.asList(cid01,cid02,cid03));
    }
}
