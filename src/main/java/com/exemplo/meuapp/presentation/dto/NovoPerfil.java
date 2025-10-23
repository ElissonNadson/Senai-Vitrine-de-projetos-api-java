package com.exemplo.meuapp.presentation.dto;

import com.exemplo.meuapp.domain.enums.UsuarioTipo;
import com.exemplo.meuapp.domain.enums.UsuariosStatus;

public record NovoPerfil(
        String usuario,
        String senha,
        String email,
        UsuarioTipo tipo,
        UsuariosStatus status,
        Boolean aceiteTermos
) {}

