package br.com.zup.security.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    public String getUserInfo(){
        return "User infromation. Department: " +
                SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String getAdminInfo() {
        return "Admin Information";
    }
}
