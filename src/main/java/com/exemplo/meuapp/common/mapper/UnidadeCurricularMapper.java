package com.exemplo.meuapp.common.mapper;

import java.util.List;

import com.exemplo.meuapp.domain.model.UnidadeCurricular;

import org.mapstruct.Mapper;

import com.exemplo.meuapp.infrastructure.persistence.entity.UnidadeCurricularEntity;

@Mapper(componentModel = "spring")
public interface UnidadeCurricularMapper {

    UnidadeCurricularEntity toEntity(UnidadeCurricular aluno);

    UnidadeCurricular toDomain(UnidadeCurricularEntity alunoEntity);

    List<UnidadeCurricular> toDomain(List<UnidadeCurricularEntity> UnidadeCurricularEntities);

    List<UnidadeCurricularEntity> toEntity(List<UnidadeCurricular> UnidadeCurricular);
}
