package br.com.project.springionic.repository;

import br.com.project.springionic.controller.domain.ProdutoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoCategoriaRepository extends JpaRepository<ProdutoCategoria, Integer> {
}
