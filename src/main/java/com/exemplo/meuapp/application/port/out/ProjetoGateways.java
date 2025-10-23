package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.model.Projeto;

import java.util.List;
import java.util.UUID;

public interface ProjetoGateways {
    Projeto save(Projeto projeto);

    Projeto getProjetoById(UUID projetoId);

    void delete(UUID projetoId);

    Projeto update(Projeto projeto);

    List<Projeto> getAllProjetos();

    List<Projeto> getProjetosByTitulo(String titulo);

    List<Projeto> getProjetosByStatus(String status);

    List<Projeto> getProjetosByCurso(String curso);

    List<Projeto> getProjetosByTurma(String turma);

    List<Projeto> getProjetosByLider(UUID liderProjetoId);

    List<Projeto> getProjetosByUnidadeCurricular(UUID unidadeCurricularId);

    boolean existsByCodigo(String codigo);

    boolean existsByTitulo(String titulo);
}