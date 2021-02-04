package br.com.project.springionic;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringIonicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat01 = new Categoria(null, "Informatica");
        Categoria cat02 = new Categoria(null, "Financeiro");
        Categoria cat03 = new Categoria(null, "Markenting");

        categoriaRepository.saveAll(Arrays.asList(cat01,cat02,cat03));
    }
}
