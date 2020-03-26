package com.example.demo.core.service;

import com.example.demo.core.model.User;
import com.example.demo.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    /**
     * Reponsavel por buscar todos os usuarios da base de dados.
     *
     * @return Todos os usuarios.
     */

    public void deleteAll(){
        userRepository.deleteAll();
    }

    public List<User> findByAll() {
        return this.userRepository.findAll();
    }

    public void save(User user) {
        this.userRepository.save(user);
    }
}
