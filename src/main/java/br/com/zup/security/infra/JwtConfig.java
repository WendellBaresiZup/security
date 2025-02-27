package br.com.zup.security.infra;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
@Configuration
public class JwtConfig {
    private static final String SECRET_KEY = "secreta";

    @Bean
    public Key key(){
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
