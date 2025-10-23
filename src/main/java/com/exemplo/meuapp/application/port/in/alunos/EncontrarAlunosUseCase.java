package com.exemplo.meuapp.application.port.in.alunos;

import java.util.List;
import java.util.UUID;

import com.exemplo.meuapp.application.port.out.AlunosGateways;
import com.exemplo.meuapp.domain.model.Alunos;

public class EncontrarAlunosUseCase {

    private AlunosGateways alunosGateways;

    public EncontrarAlunosUseCase(AlunosGateways alunosGateways) {
        this.alunosGateways = alunosGateways;
    }

    public Alunos buscarPorUuid(UUID uuid) {
        return alunosGateways.getAlunos(uuid);
    }

    public Alunos buscarPorMatricula(String matricula) {

        return alunosGateways.getAlunosByMatricula(matricula);
    }

    public List<Alunos> buscarTodos() {
        return alunosGateways.getAllAlunos();
    }

    public Alunos buscarPorUsuario(UUID usuario) {
        return alunosGateways.getAlunosByUsuarioId(usuario);
    }


}
