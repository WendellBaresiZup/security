package br.com.zup.security.infra;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
    //private static final String SECRET_KEY = "secreta";
    private final Key key;

    @Autowired
    public JwtUtil(Key key) {
        this.key = key;
    }

    public String createToken(String username, String department, String role) {
                return Jwts.builder()
                .setSubject(username)
                        .claim("department", department)
                        .claim("role" , role)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                        .signWith(key, SignatureAlgorithm.HS256)
                        .compact();
    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.resolve(claims);
    }

    public interface ClaimsResolver<T>{
        T resolve(Claims claims);
    }

    public Claims extractAllClaims(String token) {
        JwtParser parser = Jwts.parser().setSigningKey(key).build();
        return parser.parseClaimsJws(token).getBody();
    }

    public String getUserNameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
