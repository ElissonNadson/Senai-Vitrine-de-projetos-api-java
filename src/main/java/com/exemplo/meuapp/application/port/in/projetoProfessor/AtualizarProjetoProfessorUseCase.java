package com.exemplo.meuapp.application.port.in.projetoProfessor;

import com.exemplo.meuapp.application.port.out.ProjetoProfessorGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.ProjetoProfessor;

import java.util.UUID;

public class AtualizarProjetoProfessorUseCase {
    private final ProjetoProfessorGateways projetoProfessorGateways;

    public AtualizarProjetoProfessorUseCase(ProjetoProfessorGateways projetoProfessorGateways) {
        this.projetoProfessorGateways = projetoProfessorGateways;
    }

    public ProjetoProfessor atualizar(UUID uuid, ProjetoProfessor projetoProfessor) {
        ProjetoProfessor projetoProfessorInDb = projetoProfessorGateways.getProjetoProfessor(uuid);
        if (projetoProfessorInDb == null) {
            throw new RegraDeNegocioException("Vínculo Projeto-Professor não encontrado.");
        }

        projetoProfessor.correct();

        if ((!projetoProfessorInDb.getProjeto().getUuid().equals(projetoProfessor.getProjeto().getUuid()) ||
                !projetoProfessorInDb.getProfessor().getUuid().equals(projetoProfessor.getProfessor().getUuid())) &&
                projetoProfessorGateways.existsByProjetoAndProfessor(
                        projetoProfessor.getProjeto().getUuid(),
                        projetoProfessor.getProfessor().getUuid())) {
            throw new RegraDeNegocioException("Já existe vínculo entre este projeto e este professor.");
        }

        projetoProfessorInDb.setProjeto(projetoProfessor.getProjeto());
        projetoProfessorInDb.setProfessor(projetoProfessor.getProfessor());
        projetoProfessorInDb.setIsOrientador(projetoProfessor.getIsOrientador());

        return projetoProfessorGateways.update(projetoProfessorInDb);
    }
}