package com.exemplo.meuapp.application.port.in.projetoAluno;

import com.exemplo.meuapp.application.port.out.ProjetoAlunoGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.ProjetoAluno;

public class CriarProjetoAlunoUseCase {
    private ProjetoAlunoGateways projetoAlunoGateways;

    public CriarProjetoAlunoUseCase(ProjetoAlunoGateways projetoAlunoGateways) {
        this.projetoAlunoGateways = projetoAlunoGateways;
    }

    public ProjetoAluno criar(ProjetoAluno projetoAluno) {
        projetoAluno.correct();

        if (projetoAluno.getProjeto() != null
                && projetoAluno.getProjeto().getStatus() != null
                && !projetoAluno.getProjeto().getStatus().name().equals("ATIVO")) {
            throw new RegraDeNegocioException("Projeto associado está inativo.");
        }

        if (projetoAluno.getAluno() != null
                && projetoAluno.getAluno().getStatus() != null
                && !projetoAluno.getAluno().getStatus().name().equals("ATIVO")) {
            throw new RegraDeNegocioException("Aluno associado está inativo.");
        }

        if (projetoAlunoGateways.existsByProjetoAndAluno(
                projetoAluno.getProjeto().getUuid(),
                projetoAluno.getAluno().getUuid())) {
            throw new RegraDeNegocioException("Já existe vínculo deste aluno com este projeto.");
        }

        return projetoAlunoGateways.save(projetoAluno);
    }
}