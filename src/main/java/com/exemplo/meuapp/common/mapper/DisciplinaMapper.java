package com.exemplo.meuapp.common.mapper;

import java.util.List;

import com.exemplo.meuapp.domain.model.Disciplina;

import org.mapstruct.Mapper;

import com.exemplo.meuapp.infrastructure.persistence.entity.DisciplinaEntity;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    DisciplinaEntity toEntity(Disciplina aluno);

    Disciplina toDomain(DisciplinaEntity alunoEntity);

    List<Disciplina> toDomain(List<DisciplinaEntity> DisciplinaEntities);

    List<DisciplinaEntity> toEntity(List<Disciplina> Disciplina);
}
