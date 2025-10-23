package com.exemplo.meuapp.application.port.in.disciplinaProjeto;

import com.exemplo.meuapp.application.port.out.DisciplinaProjetoGateways;
import com.exemplo.meuapp.domain.model.DisciplinaProjeto;

import java.util.List;
import java.util.UUID;

public class EncontrarDisciplinaProjetoUseCase {
    private final DisciplinaProjetoGateways disciplinaProjetoGateways;

    public EncontrarDisciplinaProjetoUseCase(DisciplinaProjetoGateways disciplinaProjetoGateways) {
        this.disciplinaProjetoGateways = disciplinaProjetoGateways;
    }

    public DisciplinaProjeto buscarPorUuid(UUID uuid) {
        return disciplinaProjetoGateways.getDisciplinaProjeto(uuid);
    }

    public List<DisciplinaProjeto> buscarTodos() {
        return disciplinaProjetoGateways.getAllDisciplinasProjetos();
    }

    public List<DisciplinaProjeto> buscarPorDisciplina(UUID disciplinaId) {
        return disciplinaProjetoGateways.getByDisciplina(disciplinaId);
    }

    public List<DisciplinaProjeto> buscarPorProjeto(UUID projetoId) {
        return disciplinaProjetoGateways.getByProjeto(projetoId);
    }

    public List<DisciplinaProjeto> buscarPorDisciplinaEProjeto(UUID disciplinaId, UUID projetoId) {
        return disciplinaProjetoGateways.getByDisciplinaAndProjeto(disciplinaId, projetoId);
    }
}