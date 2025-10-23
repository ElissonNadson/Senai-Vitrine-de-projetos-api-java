package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.model.Usuarios;

import java.util.List;
import java.util.UUID;

public interface UsuariosGateways {
    Usuarios save(Usuarios usuarios);

    Usuarios getUsuariosByEmail(String email);

    void delete(UUID usuariosId);

    Usuarios update(Usuarios usuarios);

    Usuarios getUsuarios(UUID usuariosId);

    Usuarios getUsuariosByMatricula(String matricula);

    List<Usuarios> getUsuariosByStatus(UsuariosStatus status);

    List<Usuarios> getAllUsuarios();
}
