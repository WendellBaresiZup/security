package br.com.zup.security.services;

import br.com.zup.security.dto.UserDTO;
import br.com.zup.security.dto.UserLoginDTO;
import br.com.zup.security.infra.JwtUtil;
import br.com.zup.security.models.User;
import br.com.zup.security.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
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
        List<User> users = userRepository.findByUserName(userLoginDTO.getUserName());

        if (!users.isEmpty()) {
            User user = users.get(0);

            if (bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
                String token = jwtUtil.createToken(user.getUserName(), "IT", "ROLE_USER");
                return Map.of("message", "usuário logado", "token", token);
            }
            return Map.of("message", "usuário inválido");
        }
        return Map.of("message", "useName inválido");
    }
}
