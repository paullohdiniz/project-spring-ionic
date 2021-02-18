package br.com.project.springionic.repository;

import br.com.project.springionic.controller.domain.Estado;
import br.com.project.springionic.controller.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
