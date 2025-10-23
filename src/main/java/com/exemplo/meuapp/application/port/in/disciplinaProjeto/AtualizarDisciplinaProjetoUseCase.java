package com.exemplo.meuapp.application.port.in.disciplinaProjeto;

import com.exemplo.meuapp.application.port.out.DisciplinaProjetoGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.DisciplinaProjeto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AtualizarDisciplinaProjetoUseCase {
    private final DisciplinaProjetoGateways disciplinaProjetoGateways;

    public AtualizarDisciplinaProjetoUseCase(DisciplinaProjetoGateways disciplinaProjetoGateways) {
        this.disciplinaProjetoGateways = disciplinaProjetoGateways;
    }

    public DisciplinaProjeto atualizar(UUID uuid, DisciplinaProjeto disciplinaProjeto) {
        DisciplinaProjeto disciplinaProjetoInDb = disciplinaProjetoGateways.getDisciplinaProjeto(uuid);
        if (disciplinaProjetoInDb == null) {
            throw new RegraDeNegocioException("Vínculo Disciplina-Projeto não encontrado.");
        }

        disciplinaProjeto.correct();

        if ((!disciplinaProjetoInDb.getDisciplina().getUuid().equals(disciplinaProjeto.getDisciplina().getUuid()) ||
                !disciplinaProjetoInDb.getProjeto().getUuid().equals(disciplinaProjeto.getProjeto().getUuid())) &&
                disciplinaProjetoGateways.existsByDisciplinaAndProjeto(
                        disciplinaProjeto.getDisciplina().getUuid(),
                        disciplinaProjeto.getProjeto().getUuid())) {
            throw new RegraDeNegocioException("Já existe vínculo entre esta disciplina e este projeto.");
        }

        disciplinaProjetoInDb.setDisciplina(disciplinaProjeto.getDisciplina());
        disciplinaProjetoInDb.setProjeto(disciplinaProjeto.getProjeto());

        return disciplinaProjetoGateways.update(disciplinaProjetoInDb);
    }
}