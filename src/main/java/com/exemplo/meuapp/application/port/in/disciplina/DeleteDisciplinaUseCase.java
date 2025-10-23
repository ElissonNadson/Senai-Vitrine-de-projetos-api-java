package com.exemplo.meuapp.application.port.in.disciplina;

import com.exemplo.meuapp.application.port.out.DisciplinaGateways;

import java.util.UUID;

public class DeleteDisciplinaUseCase {
    private DisciplinaGateways disciplinaGateways;

    public DeleteDisciplinaUseCase(DisciplinaGateways disciplinaGateways) {
        this.disciplinaGateways = disciplinaGateways;
    }

    public void deletar(UUID uuid) {
        disciplinaGateways.delete(uuid);
    }
}
