package com.exemplo.meuapp.presentation.dto;

import com.exemplo.meuapp.domain.enums.UsuarioTipo;

public record CadastroAlunoDTO(
        String login,
        String senha,
        String nome,
        String email,
        UsuarioTipo tipo,
        boolean aceiteTermos
) {}
