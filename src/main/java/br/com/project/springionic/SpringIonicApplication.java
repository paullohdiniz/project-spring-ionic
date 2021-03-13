package br.com.project.springionic;

import br.com.project.springionic.controller.domain.*;
import br.com.project.springionic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner {




    public static void main(String[] args) {
        SpringApplication.run(SpringIonicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }


}
