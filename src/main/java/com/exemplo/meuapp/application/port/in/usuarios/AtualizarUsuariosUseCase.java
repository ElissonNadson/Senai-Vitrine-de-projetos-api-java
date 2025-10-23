package com.exemplo.meuapp.application.port.in.usuarios;

import java.util.UUID;

import com.exemplo.meuapp.application.port.out.UsuariosGateways;
import com.exemplo.meuapp.domain.model.Usuarios;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AtualizarUsuariosUseCase {

    private UsuariosGateways usuariosGateways;

    public AtualizarUsuariosUseCase(UsuariosGateways usuariosGateways) {
        this.usuariosGateways = usuariosGateways;
    }

    public Usuarios atualizar(UUID uuid, Usuarios usuarios) {
        Usuarios usuarioInDb = usuariosGateways.getUsuarios(uuid);

        usuarioInDb.setSenha(usuarios.getSenha());
        usuarioInDb.setEmail(usuarios.getEmail());
        usuarioInDb.setUsuario(usuarios.getUsuario());
        usuarioInDb.setStatus(usuarios.getStatus());
        usuarioInDb.setSenha(new BCryptPasswordEncoder().encode(usuarioInDb.getSenha()));
        return usuariosGateways.update(usuarios);
    }
}
