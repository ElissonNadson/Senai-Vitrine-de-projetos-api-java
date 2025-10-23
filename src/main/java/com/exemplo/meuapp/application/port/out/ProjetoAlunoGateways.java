package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.model.ProjetoAluno;

import java.util.List;
import java.util.UUID;

public interface ProjetoAlunoGateways {
     ProjetoAluno save(ProjetoAluno projetoAluno);
     ProjetoAluno getProjetoAluno(UUID projetoAlunoId);
     void delete(UUID projetoAlunoId);
     ProjetoAluno update(ProjetoAluno projetoAluno);
     List<ProjetoAluno> getAllProjetosAlunos();
     List<ProjetoAluno> getProjetosAlunosByProjeto(UUID projetoId);
     List<ProjetoAluno> getProjetosAlunosByAluno(UUID alunoId);

    boolean existsByProjetoAndAluno(UUID uuid, UUID uuid1);
}
