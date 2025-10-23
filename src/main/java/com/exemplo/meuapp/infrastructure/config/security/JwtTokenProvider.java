package com.exemplo.meuapp.infrastructure.config.security;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.exemplo.meuapp.infrastructure.persistence.entity.UsuariosEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.exemplo.meuapp.presentation.dto.PerfilUsuario;
import com.exemplo.meuapp.presentation.dto.TokensDTO;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Component
public class JwtTokenProvider  {

    @Value("${api.security.token.secret}")
    private String secret;



    public String generateAccessToken(PerfilUsuario usuariosEntity) {
        return generateToken(usuariosEntity, genAccessTokenExpiry());
    }

    public String generateRefreshToken(PerfilUsuario usuariosEntity) {
        return generateToken(usuariosEntity, genRefreshTokenExpiry());
    }

    public TokensDTO generateTokens(PerfilUsuario usuariosEntity) {
        String accessToken = generateAccessToken(usuariosEntity);
        String refreshToken = generateRefreshToken(usuariosEntity);
        return new TokensDTO(accessToken, refreshToken, usuariosEntity);
    }

    public String validateAccessToken(String accessToken) {
        return validateToken(accessToken, "access");
    }

    public String validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, "refresh");
    }

    public String validateToken(String token, String type) {
        try {
            Algorithm algorithms = Algorithm.HMAC256(secret);
            return JWT.require(algorithms)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private String generateToken(PerfilUsuario usuariosEntity , Instant expiry) {
        try {
            Algorithm algorithms = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuariosEntity.email())
                    .withExpiresAt(expiry)
                    .sign(algorithms);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    private Instant genAccessTokenExpiry() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant genRefreshTokenExpiry() {
        return LocalDateTime.now().plusDays(15).toInstant(ZoneOffset.of("-03:00"));
    }

}
