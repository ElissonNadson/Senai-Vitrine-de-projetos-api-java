package com.exemplo.meuapp.common.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.exemplo.meuapp.domain.model.Usuarios;
import com.exemplo.meuapp.infrastructure.persistence.entity.UsuariosEntity;
import com.exemplo.meuapp.presentation.dto.NovoPerfil;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuariosMapper {

    UsuariosEntity toEntity(Usuarios usuarios);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "criadoEm", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "atualizadoEm", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "tipo", expression = "java(novoPerfil.tipo() != null ? novoPerfil.tipo() : com.exemplo.meuapp.domain.enums.UsuarioTipo.ALUNO)")
    Usuarios toDomain(NovoPerfil novoPerfil);

    Usuarios toDomain(UsuariosEntity usuariosEntity);

    List<Usuarios> toDomain(List<UsuariosEntity> usuariosEntities);

    List<UsuariosEntity> toEntity(List<Usuarios> usuarios);
}
