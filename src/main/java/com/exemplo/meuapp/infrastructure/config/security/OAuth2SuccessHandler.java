package com.exemplo.meuapp.infrastructure.config.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.exemplo.meuapp.application.port.in.usuarios.CriarUsuariosUseCase;
import com.exemplo.meuapp.application.port.in.usuarios.EncontrarUsuariosUseCase;
import com.exemplo.meuapp.common.mapper.UsuariosMapper;
import com.exemplo.meuapp.domain.enums.UsuarioTipo;
import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.model.Usuarios;
import com.exemplo.meuapp.presentation.dto.TokensDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtProvider;
    private EncontrarUsuariosUseCase encontrarUsuariosUseCase;
    private CriarUsuariosUseCase criarUsuariosUseCase;
    private UsuariosMapper usuariosMapper;
    private final ObjectMapper objectMapper;
    public OAuth2SuccessHandler(
            JwtTokenProvider jwtProvider,
            EncontrarUsuariosUseCase encontrarUsuariosUseCase,
            CriarUsuariosUseCase criarUsuariosUseCase,
            UsuariosMapper usuariosMapper,
            ObjectMapper objectMapper) {
        this.jwtProvider = jwtProvider;
        this.encontrarUsuariosUseCase = encontrarUsuariosUseCase;
        this.criarUsuariosUseCase = criarUsuariosUseCase;
        this.usuariosMapper = usuariosMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
                                        Authentication auth) throws IOException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) auth;

        OAuth2User oauthUser = oauthToken.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String nome = oauthUser.getAttribute("given_name");
        if (encontrarUsuariosUseCase.buscarPorEmailUser(email) == null) {            criarUsuariosUseCase.criar(Usuarios.builder()
                    .email(email)
                    .usuario(nome)
                    .tipo(UsuarioTipo.ALUNO)  // 🔧 CORRIGIDO: ALUNO ao invés de VISITANTE
                    .status(UsuariosStatus.ATIVO)
                    .senha(nome.replaceAll("\\s", "") + "@Senai")
                    .build());
        }
        TokensDTO tokens = jwtProvider.generateTokens(
                encontrarUsuariosUseCase.buscarPorEmail(email)
        );

        String json = objectMapper.writeValueAsString(tokens);

        res.setStatus(HttpServletResponse.SC_OK);
        res.setContentType("application/json");
        res.getWriter().write(json);
    }
}
