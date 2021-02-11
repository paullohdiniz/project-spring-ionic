package br.com.project.springionic.controller.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @OneToMany(mappedBy = "categoria")
    private Set<ProdutoCategoria> produtosCategoria = new HashSet<>();

//    @JsonManagedReference
//    @ManyToMany(mappedBy = "categorias")
//    private List<Produto> produtos = new ArrayList<>();

}
