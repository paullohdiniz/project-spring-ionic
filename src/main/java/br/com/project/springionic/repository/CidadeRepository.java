package br.com.project.springionic.repository;

import br.com.project.springionic.controller.domain.Cidade;
import br.com.project.springionic.controller.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
