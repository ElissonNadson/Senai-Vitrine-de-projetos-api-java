package com.exemplo.meuapp.application.port.in.projetoProfessor;

import com.exemplo.meuapp.application.port.out.ProjetoProfessorGateways;
import com.exemplo.meuapp.domain.model.ProjetoProfessor;

import java.util.List;
import java.util.UUID;

public class EncontrarProjetoProfessorUseCase {

    private final ProjetoProfessorGateways projetoProfessorGateways;

    public EncontrarProjetoProfessorUseCase(ProjetoProfessorGateways projetoProfessorGateways) {
        this.projetoProfessorGateways = projetoProfessorGateways;
    }

    public ProjetoProfessor buscarPorUUID(UUID projetoProfessorId) {
        return projetoProfessorGateways.getProjetoProfessor(projetoProfessorId);
    }

    public List<ProjetoProfessor> buscarTodos() {
        return projetoProfessorGateways.getAllProjetosProfessores();
    }

    public List<ProjetoProfessor> buscarPorProjeto(UUID projetoId) {
        return projetoProfessorGateways.getByProjeto(projetoId);
    }

    public List<ProjetoProfessor> buscarPorProfessor(UUID professorId) {
        return projetoProfessorGateways.getByProfessor(professorId);
    }
}
