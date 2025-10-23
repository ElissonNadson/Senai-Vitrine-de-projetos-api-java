package com.exemplo.meuapp.application.port.in.usuarios;

import com.exemplo.meuapp.application.port.out.UsuariosGateways;

import java.util.UUID;

public class DeletarUsuariosUseCase {
    private UsuariosGateways usuariosGateways;

    public DeletarUsuariosUseCase(UsuariosGateways usuariosGateways) {
        this.usuariosGateways = usuariosGateways;
    }

    public void deletar(UUID uuid) {
        usuariosGateways.delete(uuid);
    }
}
