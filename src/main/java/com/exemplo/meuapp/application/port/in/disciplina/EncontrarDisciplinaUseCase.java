package com.exemplo.meuapp.application.port.in.disciplina;

import com.exemplo.meuapp.application.port.out.DisciplinaGateways;
import com.exemplo.meuapp.domain.model.Disciplina;

import java.util.List;
import java.util.UUID;

public class EncontrarDisciplinaUseCase {
    private final DisciplinaGateways disciplinaGateways;

    public EncontrarDisciplinaUseCase(DisciplinaGateways disciplinaGateways) {
        this.disciplinaGateways = disciplinaGateways;
    }

    public Disciplina buscarPorUuid(UUID uuid) {
        return disciplinaGateways.getDisciplina(uuid);
    }

    public List<Disciplina> buscarTodas() {
        return disciplinaGateways.getAllDisciplinas();
    }

    public List<Disciplina> buscarPorNome(String nome) {
        return disciplinaGateways.getDisciplinaByNome(nome);
    }

    public List<Disciplina> buscarPorProfessor(UUID professorId) {
        return disciplinaGateways.getDisciplinaByProfessor(professorId);
    }
}