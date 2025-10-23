package com.exemplo.meuapp.application.port.in.disciplinaProjeto;

import com.exemplo.meuapp.application.port.out.DisciplinaProjetoGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.DisciplinaProjeto;

public class CriarDisciplinaProjetoUseCase {
    private DisciplinaProjetoGateways disciplinaProjetoGateways;

    public CriarDisciplinaProjetoUseCase(DisciplinaProjetoGateways disciplinaProjetoGateways) {
        this.disciplinaProjetoGateways = disciplinaProjetoGateways;
    }

    public DisciplinaProjeto criar(DisciplinaProjeto disciplinaProjeto) {
        disciplinaProjeto.correct();


        if (disciplinaProjetoGateways.existsByDisciplinaAndProjeto(
                disciplinaProjeto.getDisciplina().getUuid(),
                disciplinaProjeto.getProjeto().getUuid())) {
            throw new RegraDeNegocioException("Já existe vínculo entre esta disciplina e este projeto.");
        }

        if (disciplinaProjeto.getProjeto() != null
                && disciplinaProjeto.getProjeto().getStatus() != null
                && !disciplinaProjeto.getProjeto().getStatus().name().equals("ATIVO")) {
            throw new RegraDeNegocioException("Projeto associado está inativo.");
        }

        return disciplinaProjetoGateways.save(disciplinaProjeto);
    }
}