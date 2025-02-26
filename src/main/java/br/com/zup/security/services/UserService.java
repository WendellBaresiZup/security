package br.com.zup.security.services;

import br.com.zup.security.models.User;
import br.com.zup.security.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
