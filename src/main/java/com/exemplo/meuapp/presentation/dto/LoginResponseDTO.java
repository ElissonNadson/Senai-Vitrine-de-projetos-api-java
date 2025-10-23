package com.exemplo.meuapp.presentation.dto;

import com.exemplo.meuapp.domain.model.Usuarios;

public class LoginResponseDTO{
    private final String accessToken;
    private final String refreshToken;
    private Usuarios users;

    public LoginResponseDTO(  Usuarios users, TokensDTO tokens) {
        this.users = users;
        this.accessToken = tokens.getAccessToken();
        this.refreshToken = tokens.getRefreshToken();
    }
    public Usuarios getUser() {
        return users;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
