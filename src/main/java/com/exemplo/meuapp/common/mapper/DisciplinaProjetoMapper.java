package com.exemplo.meuapp.common.mapper;

import java.util.List;

import com.exemplo.meuapp.domain.model.DisciplinaProjeto;

import org.mapstruct.Mapper;

import com.exemplo.meuapp.infrastructure.persistence.entity.DisciplinaProjetoEntity;

@Mapper(componentModel = "spring")
public interface DisciplinaProjetoMapper {

    DisciplinaProjetoEntity toEntity(DisciplinaProjeto aluno);

    DisciplinaProjeto toDomain(DisciplinaProjetoEntity alunoEntity);

    List<DisciplinaProjeto> toDomain(List<DisciplinaProjetoEntity> DisciplinaProjetoEntities);

    List<DisciplinaProjetoEntity> toEntity(List<DisciplinaProjeto> DisciplinaProjeto);
}
