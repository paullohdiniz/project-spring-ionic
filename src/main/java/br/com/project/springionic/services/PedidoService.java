package br.com.project.springionic.services;

import br.com.project.springionic.controller.domain.*;
import br.com.project.springionic.repository.CategoriaRepository;
import br.com.project.springionic.repository.ItemPedidoRepository;
import br.com.project.springionic.repository.PagamentoRepository;
import br.com.project.springionic.repository.PedidoRepository;
import br.com.project.springionic.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BoletoService boletoService;

    public Pedido findById(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        //return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Nao Encontado"));
    }

    public List<Pedido> findAll(){
        List<Pedido> pedidoList = pedidoRepository.findAll();
        return pedidoList;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
//        UserSS user = UserService.authenticated();
//        if (user == null) {
//            throw new AuthorizationException("Acesso negado");
//        }
//        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
//        Cliente cliente =  clienteService.find(user.getId());
//        return repo.findByCliente(cliente, pageRequest);
        return null;
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstancia(new Date());
        obj.setCliente(clienteService.findById(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamentoEnum.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstancia());
        }

        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());

        for (ItemPedido ip : obj.getItensPedido()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.findById(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }

        itemPedidoRepository.saveAll(obj.getItensPedido());
        //emailService.sendOrderConfirmationEmail(obj);
        return obj;
    }
}
