package com.exemplo.meuapp.application.port.in.projetoAluno;

import com.exemplo.meuapp.application.port.out.ProjetoAlunoGateways;
import java.util.UUID;

public class DeletarProjetoAlunoUseCase {
    private ProjetoAlunoGateways projetoAlunoGateways;

    public DeletarProjetoAlunoUseCase(ProjetoAlunoGateways projetoAlunoGateways) {
        this.projetoAlunoGateways = projetoAlunoGateways;
    }

    public void deletar(UUID uuid) {
        projetoAlunoGateways.delete(uuid);
    }
}