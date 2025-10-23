package com.exemplo.meuapp.application.port.in.disciplinaProjeto;

import com.exemplo.meuapp.application.port.out.DisciplinaProjetoGateways;

import java.util.UUID;

public class DeletarDisciplinaProjetoUseCase {
    private DisciplinaProjetoGateways disciplinaProjetoGateways;

    public DeletarDisciplinaProjetoUseCase(DisciplinaProjetoGateways disciplinaProjetoGateways) {
        this.disciplinaProjetoGateways = disciplinaProjetoGateways;
    }

    public void deletar(UUID uuid) {
        disciplinaProjetoGateways.delete(uuid);
    }
}
