package com.exemplo.meuapp.application.port.in.projeto;

import com.exemplo.meuapp.application.port.out.ProjetoGateways;
import com.exemplo.meuapp.domain.model.Projeto;

import java.util.List;
import java.util.UUID;

public class EncontrarProjetoUseCase {

    private final ProjetoGateways projetoGateways;

    public EncontrarProjetoUseCase(ProjetoGateways projetoGateways) {
        this.projetoGateways = projetoGateways;
    }

    public Projeto buscarPorUUID(UUID projetoId) {
        return projetoGateways.getProjetoById(projetoId);
    }

    public List<Projeto> buscarTodos() {
        return projetoGateways.getAllProjetos();
    }

    public List<Projeto> buscarPorTitulo(String titulo) {
        return projetoGateways.getProjetosByTitulo(titulo);
    }

    public List<Projeto> buscarPorStatus(String status) {
        return projetoGateways.getProjetosByStatus(status);
    }

    public List<Projeto> buscarPorCurso(String curso) {
        return projetoGateways.getProjetosByCurso(curso);
    }

    public List<Projeto> buscarPorTurma(String turma) {
        return projetoGateways.getProjetosByTurma(turma);
    }

    public List<Projeto> buscarPorLider(UUID liderProjetoId) {
        return projetoGateways.getProjetosByLider(liderProjetoId);
    }

    public List<Projeto> buscarPorUnidadeCurricular(UUID unidadeCurricularId) {
        return projetoGateways.getProjetosByUnidadeCurricular(unidadeCurricularId);
    }
}
