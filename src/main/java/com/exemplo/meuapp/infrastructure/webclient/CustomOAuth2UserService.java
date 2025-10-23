package com.exemplo.meuapp.infrastructure.webclient;


import com.exemplo.meuapp.application.port.in.usuarios.CriarUsuariosUseCase;
import com.exemplo.meuapp.application.port.in.usuarios.EncontrarUsuariosUseCase;
import com.exemplo.meuapp.domain.enums.UsuarioTipo;
import com.exemplo.meuapp.domain.model.Usuarios;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private CriarUsuariosUseCase criarUsuariosUseCase;
    private EncontrarUsuariosUseCase encontrarUsuariosUseCase;
    public CustomOAuth2UserService(
            CriarUsuariosUseCase criarUsuariosUseCase,
            EncontrarUsuariosUseCase encontrarUsuariosUseCase
    ) {
        this.criarUsuariosUseCase = criarUsuariosUseCase;
        this.encontrarUsuariosUseCase = encontrarUsuariosUseCase;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest req) {
        OAuth2User user = super.loadUser(req);
        String email = user.getAttribute("email");
        if (encontrarUsuariosUseCase.buscarPorEmail(email) == null) {
            Usuarios novo = new Usuarios();
            novo.setEmail(email);
            novo.setUsuario(user.getName());
            novo.setTipo(UsuarioTipo.ALUNO);
            novo.setSenha(user.getName().replaceAll("\\s", "") + "@Senai");
            criarUsuariosUseCase.criar(novo);
        }

        return user;
    }
}
