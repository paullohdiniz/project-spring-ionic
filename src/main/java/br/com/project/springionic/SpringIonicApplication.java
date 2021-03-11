package br.com.project.springionic;

import br.com.project.springionic.controller.domain.*;
import br.com.project.springionic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(SpringIonicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        createUser();
        Categoria cat01 = new Categoria(null, "Informatica",null);
        Categoria cat02 = new Categoria(null, "Financeiro", null);

        Produto produto01 = new Produto(null, "Computador", 3.8, null, null);
        Produto produto02 = new Produto(null, "Impressora", 3.8, null, null);
        Produto produto03 = new Produto(null, "Mouse", 3.8, null, null);
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

        Cliente cli = new Cliente(null, "Paulo Diniz", "paullo@gmail.com", "05503455575", TipoClienteEnum.PESSOA_FISICA, null, telefones);

        Endereco end01 = new Endereco(null, "Rua Flores","400","Apt 01", "Jardim", "011652145", cli, cid01);
        Endereco end02 = new Endereco(null, "Rua Ministro","25","Beco", "Jardins", "05365215", cli, cid02);

        cli.setEnderecos(Arrays.asList(end01, end02));

        clienteRepository.saveAll(Arrays.asList(cli));
        enderecoRepository.saveAll(Arrays.asList(end01,end02));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped01 = new Pedido(null, sdf.parse("20/02/2021 10:20"), null, cli, end01, null);
        Pedido ped02 = new Pedido(null, sdf.parse("19/02/2021 10:20"), null, cli, end02, null);

        Pagamento pag01 = new PagamentoComCartao(null, EstadoPagamentoEnum.PENDENTE, ped01, 3, sdf.parse("21/02/2021 11:22"));
        ped01.setPagamento(pag01);

        Pagamento pag02 = new PagamentoComBoleto(null, EstadoPagamentoEnum.QUITADO, ped02, sdf.parse("21/02/2021 11:22"), sdf.parse("25/02/2021 11:22"));
        ped02.setPagamento(pag02);

        cli.setPedidos(Arrays.asList(ped01,ped02));

        pedidoRepository.saveAll(Arrays.asList(ped01,ped02));
        pagamentoRepository.saveAll(Arrays.asList(pag01,pag02));
//
        ItemPedido ip01 = new ItemPedido(ped01,produto01,0.00,1, 200.0);
        ItemPedido ip02 = new ItemPedido(ped01,produto03,0.00,2, 80.0);
        ItemPedido ip03 = new ItemPedido(ped02,produto02,100.00,1, 150.0);

        ped01.setItensPedido(Arrays.asList(ip01,ip02));
        ped02.setItensPedido(Arrays.asList(ip01,ip03));

        produto01.setItemPedidoSet(Arrays.asList(ip01));
        produto02.setItemPedidoSet(Arrays.asList(ip03));
        produto03.setItemPedidoSet(Arrays.asList(ip02));

        itemPedidoRepository.saveAll(Arrays.asList(ip01,ip02,ip03));
    }

    private void createUser() {
        List<User> users = Stream.of(
          new User(null, "Paulo Diniz", "paullohdiniz@gmail.com","1234"),
          new User(null, "Kelly Diniz", "kellydiniz@gmail.com","1234")
        ).collect(Collectors.toList());

        userRepository.saveAll(users);

    }
}
