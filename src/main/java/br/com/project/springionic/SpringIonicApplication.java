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
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

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
//
        Cidade cid01 = new Cidade(null, "Uberlandia", est01);
        Cidade cid02 = new Cidade(null, "Sao Paulo", est02);
        Cidade cid03 = new Cidade(null, "Campinas", est02);
//
        est01.setCidades(Arrays.asList(cid01));
        est02.setCidades(Arrays.asList(cid02,cid03));
//
        estadoRepository.saveAll(Arrays.asList(est01,est02));
        cidadeRepository.saveAll(Arrays.asList(cid01,cid02,cid03));

        Set<String> telefones = new HashSet<>();
        telefones.add("11948475478");
        telefones.add("11958652145");

        Cliente cli = new Cliente(null, "Paulo Diniz", "paullo@gmail.com", "05503455575", TipoClienteEnum.PESSOA_FISICA,null, telefones);

        Endereco end01 = new Endereco(null, "Rua Flores","400","Apt 01", "Jardim", "011652145", cli, cid01);
        Endereco end02 = new Endereco(null, "Rua Ministro","25","Beco", "Jardins", "05365215", cli, cid02);

        cli.setEnderecos(Arrays.asList(end01, end02));

        clienteRepository.saveAll(Arrays.asList(cli));
        enderecoRepository.saveAll(Arrays.asList(end01,end02));
    }
}
