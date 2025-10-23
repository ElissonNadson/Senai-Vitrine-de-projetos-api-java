package com.exemplo.meuapp.application.port.in.projetoProfessor;

import com.exemplo.meuapp.application.port.out.ProjetoProfessorGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.ProjetoProfessor;

public class CriarProjetoProfessorUseCase {
    private ProjetoProfessorGateways projetoProfessorGateways;

    public CriarProjetoProfessorUseCase(ProjetoProfessorGateways projetoProfessorGateways) {
        this.projetoProfessorGateways = projetoProfessorGateways;
    }

    public ProjetoProfessor criar(ProjetoProfessor projetoProfessor) {
        projetoProfessor.correct();

        if (projetoProfessor.getProjeto() != null
                && projetoProfessor.getProjeto().getStatus() != null
                && !projetoProfessor.getProjeto().getStatus().name().equals("ATIVO")) {
            throw new RegraDeNegocioException("Projeto associado está inativo.");
        }

        if (projetoProfessor.getProfessor() != null
                && projetoProfessor.getProfessor().getUsuarios() != null
                && projetoProfessor.getProfessor().getUsuarios().getStatus() != null
                && !projetoProfessor.getProfessor().getUsuarios().getStatus().name().equals("ATIVO")) {
            throw new RegraDeNegocioException("Professor associado está inativo.");
        }

        if (projetoProfessorGateways.existsByProjetoAndProfessor(
                projetoProfessor.getProjeto().getUuid(),
                projetoProfessor.getProfessor().getUuid())) {
            throw new RegraDeNegocioException("Já existe vínculo deste professor com este projeto.");
        }

        // Só pode haver um orientador por projeto
        if (Boolean.TRUE.equals(projetoProfessor.getIsOrientador())
                && projetoProfessorGateways.existsOrientadorByProjeto(projetoProfessor.getProjeto().getUuid())) {
            throw new RegraDeNegocioException("Já existe um orientador para este projeto.");
        }

        return projetoProfessorGateways.save(projetoProfessor);
    }
}