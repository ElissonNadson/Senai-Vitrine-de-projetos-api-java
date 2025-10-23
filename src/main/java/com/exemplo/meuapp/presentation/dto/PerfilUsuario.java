package com.exemplo.meuapp.presentation.dto;

import java.time.LocalDateTime;

import com.exemplo.meuapp.domain.enums.UsuarioTipo;
import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.model.Endereco;

public record PerfilUsuario(
    String uuid,
    String nome,
    String email,
    UsuarioTipo tipo,
    String telefonePessoal,
    String telefoneProfissional,
    String linkedin,
    Endereco endereco,
    UsuariosStatus status,
    LocalDateTime criadoEm,
    LocalDateTime atualizadoEm,
    String matricula,
    String curso,
    String especialidade,
    String departamento
) {

}
