package br.com.zup.security.controllers;

import br.com.zup.security.infra.JwtUtil;
import br.com.zup.security.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

public class AdminController {
    private AdminService adminService;
    private JwtUtil jwtUtil;

    public ResponseEntity<?> getAdmin(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            String role = jwtUtil.extractClaim(token, claims -> claims.get("role", String.class));

            if ("ROLE_ADMIN".equals(role)) {
                return ResponseEntity.ok().body("Bem vindo ao painel de administração");
            }
        }
        return ResponseEntity.status(403).body("Acesso proibido. Você não tem permissão");
    }

    @GetMapping("/admin")
    public String adminAcess(){
        return "Conteúdo de administrador";
    }
}
