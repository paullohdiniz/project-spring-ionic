package br.com.project.springionic.services;

import br.com.project.springionic.controller.domain.User;
import br.com.project.springionic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(final Pageable pageable){
        Page<User> userList = userRepository.findAll(pageable);
        return userList;
    }

    public User save(final User user){
        return userRepository.save(user);
    }

    public void deleteById(final Long id){
        userRepository.deleteById(Integer.valueOf(id.intValue()));
    }

    public Optional<User> getUserById(final Long id){
        return userRepository.findById(id.intValue());
    }
}
