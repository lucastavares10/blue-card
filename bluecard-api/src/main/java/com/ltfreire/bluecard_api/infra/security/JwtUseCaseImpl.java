package com.ltfreire.bluecard_api.infra.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.ltfreire.bluecard_api.domain.interfaces.useCases.security.JwtUseCase;
import com.ltfreire.bluecard_api.infra.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class JwtUseCaseImpl implements JwtUseCase {

    private final SecretKey secretKey;

    public JwtUseCaseImpl(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant expiration = now.plus(1, ChronoUnit.DAYS);

        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .claim("role", user.getRole().name())
                .claim("name", user.getName())
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }


    public String validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);

            return claimsJws.getPayload().getSubject();
        } catch (JwtException e) {
            System.out.println("Token inválido: " + e.getMessage());
            return null;
        }
    }
}
