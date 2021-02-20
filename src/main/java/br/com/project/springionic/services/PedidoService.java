package br.com.project.springionic.services;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.Pedido;
import br.com.project.springionic.repository.CategoriaRepository;
import br.com.project.springionic.repository.PedidoRepository;
import br.com.project.springionic.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findById(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        //return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Nao Encontado"));
    }

    public List<Pedido> findAll(){
        List<Pedido> pedidoList = pedidoRepository.findAll();
        return pedidoList;
    }
}
