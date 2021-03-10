package br.com.project.springionic.controller.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String cpfCnpj;
    private TipoClienteEnum tipoClienteEnum;

    @JsonIgnore
    private String senha;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(Integer id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoClienteEnum = null;
        this.telefones = null;
        this.pedidos = null;
    }

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoClienteEnum tipo, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfOuCnpj;
        this.tipoClienteEnum = tipo;
        this.senha = senha;
        //addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoClienteEnum tipo, String senha, Set<String> telefones) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfOuCnpj;
        this.tipoClienteEnum = tipo;
        this.senha = senha;
        this.telefones = telefones;
        //addPerfil(Perfil.CLIENTE);
    }
}
