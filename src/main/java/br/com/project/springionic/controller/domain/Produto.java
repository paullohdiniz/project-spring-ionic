package br.com.project.springionic.controller.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    @OneToMany(mappedBy = "produto")
    private Set<ProdutoCategoria> categoriasProduto = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itemPedidoSet = new HashSet<>();

    @JsonIgnore
    public List<Pedido> getPedidos(){
        List<Pedido> pedidos = new ArrayList<>();
        for(ItemPedido x : itemPedidoSet){
            pedidos.add(x.getPedido());
        }
        return pedidos;
    }
//    @JsonBackReference
//    @ManyToMany
//    @JoinTable(name = "PRODUTO_CATEGORIA",
//              joinColumns = @JoinColumn(name = "categoria_id"))
//    private List<Categoria> categorias = new ArrayList<>();
}
