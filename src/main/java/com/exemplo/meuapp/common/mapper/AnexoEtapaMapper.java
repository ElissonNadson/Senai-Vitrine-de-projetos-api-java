package com.exemplo.meuapp.common.mapper;

import java.util.List;

import com.exemplo.meuapp.domain.model.AnexoEtapa;

import org.mapstruct.Mapper;

import com.exemplo.meuapp.infrastructure.persistence.entity.AnexoEtapaEntity;

@Mapper(componentModel = "spring")
public interface AnexoEtapaMapper {

    AnexoEtapaEntity toEntity(AnexoEtapa aluno);

    AnexoEtapa toDomain(AnexoEtapaEntity alunoEntity);

    List<AnexoEtapa> toDomain(List<AnexoEtapaEntity> AnexoEtapaEntities);

    List<AnexoEtapaEntity> toEntity(List<AnexoEtapa> AnexoEtapa);
}
