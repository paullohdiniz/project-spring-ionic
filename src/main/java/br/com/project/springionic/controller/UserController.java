package br.com.project.springionic.controller;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.User;
import br.com.project.springionic.services.CategoriaService;
import br.com.project.springionic.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    public UserController(UserService user){
        this.userService = user;
    }

    @GetMapping
    public Page<User> listar(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return userService.findAll(pageable);
    }

    @GetMapping("/")
    public List<User> welcome(){
        return userService.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping
    public User update(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
