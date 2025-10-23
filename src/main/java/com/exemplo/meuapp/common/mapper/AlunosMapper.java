package com.exemplo.meuapp.common.mapper;

import java.util.List;

import com.exemplo.meuapp.domain.model.Alunos;
import com.exemplo.meuapp.infrastructure.persistence.entity.AlunosEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunosMapper {

     AlunosEntity toEntity(Alunos alunos);

    Alunos toDomain(AlunosEntity alunosEntity);

    List<Alunos> toDomain(List<AlunosEntity> alunosEntities);

    List<AlunosEntity> toEntity(List<Alunos> alunos);
}
