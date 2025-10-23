package com.exemplo.meuapp.common.mapper;

import java.util.List;

import com.exemplo.meuapp.domain.model.Projeto;

import org.mapstruct.Mapper;

import com.exemplo.meuapp.infrastructure.persistence.entity.ProjetoEntity;

@Mapper(componentModel = "spring")
public interface ProjetoMapper {

    ProjetoEntity toEntity(Projeto aluno);

    Projeto toDomain(ProjetoEntity alunoEntity);

    List<Projeto> toDomain(List<ProjetoEntity> ProjetoEntities);

    List<ProjetoEntity> toEntity(List<Projeto> Projeto);
}
