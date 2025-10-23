package com.exemplo.meuapp.application.port.in.projetoAluno;

import com.exemplo.meuapp.application.port.out.ProjetoAlunoGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.ProjetoAluno;

import java.util.UUID;

public class AtualizarProjetoAlunoUseCase {
    private final ProjetoAlunoGateways projetoAlunoGateways;

    public AtualizarProjetoAlunoUseCase(ProjetoAlunoGateways projetoAlunoGateways) {
        this.projetoAlunoGateways = projetoAlunoGateways;
    }

    public ProjetoAluno atualizar(UUID uuid, ProjetoAluno projetoAluno) {
        ProjetoAluno projetoAlunoInDb = projetoAlunoGateways.getProjetoAluno(uuid);
        if (projetoAlunoInDb == null) {
            throw new RegraDeNegocioException("Vínculo Projeto-Aluno não encontrado.");
        }

        projetoAluno.correct();

        if ((!projetoAlunoInDb.getProjeto().getUuid().equals(projetoAluno.getProjeto().getUuid()) ||
                !projetoAlunoInDb.getAluno().getUuid().equals(projetoAluno.getAluno().getUuid())) &&
                projetoAlunoGateways.existsByProjetoAndAluno(
                        projetoAluno.getProjeto().getUuid(),
                        projetoAluno.getAluno().getUuid())) {
            throw new RegraDeNegocioException("Já existe vínculo entre este projeto e este aluno.");
        }

        projetoAlunoInDb.setProjeto(projetoAluno.getProjeto());
        projetoAlunoInDb.setAluno(projetoAluno.getAluno());
        return projetoAlunoGateways.update(projetoAlunoInDb);
    }
}