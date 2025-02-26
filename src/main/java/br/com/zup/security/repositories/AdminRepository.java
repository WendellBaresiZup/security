package br.com.zup.security.repositories;

import br.com.zup.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);
}
