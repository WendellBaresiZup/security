package br.com.zup.security.services;

import br.com.zup.security.dto.UserDTO;
import br.com.zup.security.dto.UserLoginDTO;
import br.com.zup.security.models.User;
import br.com.zup.security.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
@Service
public class UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(UserDTO userDTO){
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());

        String passwordEncoded = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        return userRepository.save(user);
    }

    public Map<String, String> login(UserLoginDTO userLoginDTO) {
        Optional<User> userOptional = userRepository.findByUserName(userLoginDTO.getUserName());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
                return Map.of("message", "usuário logado");
            }
            return Map.of("message","usuário inválido");
        }
        return Map.of("message", "userName inválido");
    }
}
