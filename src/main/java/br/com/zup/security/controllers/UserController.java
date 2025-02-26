package br.com.zup.security.controllers;

import br.com.zup.security.dto.Role;
import br.com.zup.security.dto.UserDTO;
import br.com.zup.security.infra.JwtUtil;
import br.com.zup.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        Optional<User> userOptional = userService.findByUserName(userDTO.getUserName());

        if (userOptional.isEmpty() || !userOptional.get().getPassword().equals(userDTO.getPassword())) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
        User user = userOptional.get();
        String token = jwtUtil.createToken(user.getUserName(), "IT", "ROLE_USER");

        return ResponseEntity.ok().body("Bearer " + token);
    }
}
