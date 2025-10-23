package com.exemplo.meuapp.application.port.in.projetoAluno;

import com.exemplo.meuapp.application.port.out.ProjetoAlunoGateways;
import com.exemplo.meuapp.domain.model.ProjetoAluno;

import java.util.List;
import java.util.UUID;

public class EncontrarProjetoAlunoUseCase {

    private final ProjetoAlunoGateways projetoAlunoGateways;

    public EncontrarProjetoAlunoUseCase(ProjetoAlunoGateways projetoAlunoGateways) {
        this.projetoAlunoGateways = projetoAlunoGateways;
    }

    public ProjetoAluno buscarPorUUID(UUID projetoAlunoId) {
        return projetoAlunoGateways.getProjetoAluno(projetoAlunoId);
    }

    public List<ProjetoAluno> buscarTodos() {
        return projetoAlunoGateways.getAllProjetosAlunos();
    }

    public List<ProjetoAluno> buscarPorProjeto(UUID projetoId) {
        return projetoAlunoGateways.getProjetosAlunosByProjeto(projetoId);
    }

    public List<ProjetoAluno> buscarPorAluno(UUID alunoId) {
        return projetoAlunoGateways.getProjetosAlunosByAluno(alunoId);
    }
}
