package com.exemplo.meuapp.common.mapper;

import java.util.List;

import com.exemplo.meuapp.domain.model.ProjetoProfessor;

import org.mapstruct.Mapper;

import com.exemplo.meuapp.infrastructure.persistence.entity.ProjetoProfessorEntity;

@Mapper(componentModel = "spring")
public interface ProjetoProfessorMapper {

    ProjetoProfessorEntity toEntity(ProjetoProfessor aluno);

    ProjetoProfessor toDomain(ProjetoProfessorEntity alunoEntity);

    List<ProjetoProfessor> toDomain(List<ProjetoProfessorEntity> ProjetoProfessorEntities);

    List<ProjetoProfessorEntity> toEntity(List<ProjetoProfessor> ProjetoProfessor);
}
