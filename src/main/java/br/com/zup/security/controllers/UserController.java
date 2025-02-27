package br.com.zup.security.controllers;
import br.com.zup.security.dto.UserDTO;
import br.com.zup.security.dto.UserLoginDTO;
import br.com.zup.security.infra.JwtUtil;
import br.com.zup.security.models.User;
import br.com.zup.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        User savedUser = userService.saveUser(userDTO);
        return ResponseEntity.ok("Usuario registrado com sucesso: " + savedUser.getUserName());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        Map<String, String> response = userService.login(userLoginDTO);

        if ("usuário logado".equals(response.get("message"))) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        String username = jwtUtil.extractUserName(token);
        String department = (String) jwtUtil.extractAllClaims(token).get("department");

        return ResponseEntity.ok(Map.of("message", "Bem-vindo , "  + username + "!", "department", department ));
    }
}
