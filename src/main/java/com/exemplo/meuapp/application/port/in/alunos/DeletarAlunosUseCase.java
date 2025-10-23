package com.exemplo.meuapp.application.port.in.alunos;

import com.exemplo.meuapp.application.port.out.AlunosGateways;

import java.util.UUID;

public class DeletarAlunosUseCase {
    private final AlunosGateways alunosGateways;

    public DeletarAlunosUseCase(AlunosGateways alunosGateways) {
        this.alunosGateways = alunosGateways;
    }

    public void deletar(UUID anexoUUID) {
        alunosGateways.delete(anexoUUID);
    }
}
