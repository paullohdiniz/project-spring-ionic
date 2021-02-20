package br.com.project.springionic.repository;

import br.com.project.springionic.controller.domain.Endereco;
import br.com.project.springionic.controller.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
