package com.exemplo.meuapp.common.mapper;

import java.util.List;

import com.exemplo.meuapp.domain.model.ProjetoAluno;

import org.mapstruct.Mapper;

import com.exemplo.meuapp.infrastructure.persistence.entity.ProjetoAlunoEntity;

@Mapper(componentModel = "spring")
public interface ProjetoAlunoMapper {

    ProjetoAlunoEntity toEntity(ProjetoAluno aluno);

    ProjetoAluno toDomain(ProjetoAlunoEntity alunoEntity);

    List<ProjetoAluno> toDomain(List<ProjetoAlunoEntity> ProjetoAlunoEntities);

    List<ProjetoAlunoEntity> toEntity(List<ProjetoAluno> ProjetoAluno);
}
