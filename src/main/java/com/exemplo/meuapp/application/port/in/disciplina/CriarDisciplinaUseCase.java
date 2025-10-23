package com.exemplo.meuapp.application.port.in.disciplina;

import com.exemplo.meuapp.application.port.out.DisciplinaGateways;
import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.Disciplina;

import java.time.LocalDateTime;

public class CriarDisciplinaUseCase {
    private DisciplinaGateways disciplinaGateways;

    public CriarDisciplinaUseCase(DisciplinaGateways disciplinaGateways) {
        this.disciplinaGateways = disciplinaGateways;
    }

    public Disciplina criar(Disciplina disciplina) {
        disciplina.correct();


        if (disciplinaGateways.existsByNome(disciplina.getNome())) {
            throw new RegraDeNegocioException("Já existe uma disciplina com este nome.");
        }


        if (!disciplina.getNome().matches("^[\\w\\sáéíóúãõâêîôûçÁÉÍÓÚÃÕÂÊÎÔÛÇ-]+$")) {
            throw new DadosInvalidosException("Nome da disciplina contém caracteres inválidos.");
        }


        if (disciplina.getProfessor() != null && disciplina.getProfessor().getStatus() != null) {
            if (!disciplina.getProfessor().getStatus().name().equals("ATIVO")) {
                throw new RegraDeNegocioException("Professor associado está inativo.");
            }
        }

        disciplina.setCriadoEm(LocalDateTime.now());
        disciplina.setAtualizadoEm(LocalDateTime.now());
        return disciplinaGateways.save(disciplina);
    }
}