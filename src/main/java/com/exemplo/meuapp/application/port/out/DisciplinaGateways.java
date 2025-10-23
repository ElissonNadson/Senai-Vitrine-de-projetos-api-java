package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.model.Disciplina;

import java.util.List;
import java.util.UUID;

public interface DisciplinaGateways {
    Disciplina save(Disciplina disciplina);

    Disciplina getDisciplina(UUID disciplinaId);

    void delete(UUID disciplinaId);

    Disciplina update(Disciplina disciplina);

    List<Disciplina> getAllDisciplinas();

    List<Disciplina> getDisciplinaByNome(String nome);

    List<Disciplina> getDisciplinaByProfessor(UUID professorId);

    boolean existsByNome(String nome);
}
