package com.exemplo.meuapp.common.mapper;

import java.util.List;

import com.exemplo.meuapp.domain.model.EtapasProjeto;

import org.mapstruct.Mapper;

import com.exemplo.meuapp.infrastructure.persistence.entity.EtapasProjetoEntity;

@Mapper(componentModel = "spring")
public interface EtapasProjetoMapper {

    EtapasProjetoEntity toEntity(EtapasProjeto aluno);

    EtapasProjeto toDomain(EtapasProjetoEntity alunoEntity);

    List<EtapasProjeto> toDomain(List<EtapasProjetoEntity> EtapasProjetoEntities);

    List<EtapasProjetoEntity> toEntity(List<EtapasProjeto> EtapasProjeto);
}
