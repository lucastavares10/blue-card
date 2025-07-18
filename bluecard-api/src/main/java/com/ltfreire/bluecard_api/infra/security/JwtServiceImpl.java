package com.ltfreire.bluecard_api.infra.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.ltfreire.bluecard_api.domain.interfaces.useCases.security.IJwtService;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class JwtServiceImpl implements IJwtService {

    private final SecretKey secretKey;

    public JwtServiceImpl(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UserModel user) {
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
