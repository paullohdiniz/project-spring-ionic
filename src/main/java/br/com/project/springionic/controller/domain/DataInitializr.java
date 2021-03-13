package br.com.project.springionic.controller.domain;

import br.com.project.springionic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    //private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            this.createUser("paulohdiniz","paulohdiniz@gmail.com","1254");
        }
    }
    private void createUser(String name, String email, String password){
        User user = new User(null, name, email, password);
        userRepository.save(user);
    }
}
