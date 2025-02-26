package br.com.zup.security.services;

import br.com.zup.security.models.User;
import br.com.zup.security.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public Optional<User> findByUserName(String userName){
        return adminRepository.findByUserName(userName);
    }
}
