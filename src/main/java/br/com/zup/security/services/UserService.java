package br.com.zup.security.services;

import br.com.zup.security.models.User;
import br.com.zup.security.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder bCryptPasswordEnconcer;

    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEnconcer) {
        this.userRepository = userRepository;
        this.bCryptPasswordEnconcer = bCryptPasswordEnconcer;
    }

    public User saveUser(User user){
        String passwordEnconder = bCryptPasswordEnconcer.encode(user.getPassword());
        user.setPassword(passwordEnconder);
        return userRepository.save(user);
    }
}
