package br.com.project.springionic.repository;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
