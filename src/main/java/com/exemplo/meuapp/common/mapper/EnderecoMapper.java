package com.exemplo.meuapp.common.mapper;

import java.util.List;

import com.exemplo.meuapp.domain.model.Endereco;

import org.mapstruct.Mapper;

import com.exemplo.meuapp.infrastructure.persistence.entity.EnderecoEntity;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoEntity toEntity(Endereco aluno);

    Endereco toDomain(EnderecoEntity alunoEntity);

    List<Endereco> toDomain(List<EnderecoEntity> EnderecoEntities);

    List<EnderecoEntity> toEntity(List<Endereco> Endereco);
}
