package br.com.wendelsegadilha.auth.services;

import br.com.wendelsegadilha.auth.entities.User;
import br.com.wendelsegadilha.auth.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> allUers() {
        return userRepository.findAll();
    }
}
