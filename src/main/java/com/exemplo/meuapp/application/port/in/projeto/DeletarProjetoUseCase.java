package com.exemplo.meuapp.application.port.in.projeto;

import com.exemplo.meuapp.application.port.out.ProjetoGateways;
import java.util.UUID;

public class DeletarProjetoUseCase {
    private ProjetoGateways projetosGateways;

    public DeletarProjetoUseCase(ProjetoGateways projetosGateways) {
        this.projetosGateways = projetosGateways;
    }

    public void deletar(UUID uuid) {
        projetosGateways.delete(uuid);
    }
}