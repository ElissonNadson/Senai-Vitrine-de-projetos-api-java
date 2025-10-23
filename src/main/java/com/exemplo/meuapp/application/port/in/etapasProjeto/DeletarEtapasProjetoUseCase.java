package com.exemplo.meuapp.application.port.in.etapasProjeto;

import com.exemplo.meuapp.application.port.out.EtapasProjetoGateways;

import java.util.UUID;

public class DeletarEtapasProjetoUseCase {
    private EtapasProjetoGateways etapasProjetoGateways;

    public DeletarEtapasProjetoUseCase(EtapasProjetoGateways etapasProjetoGateways) {
        this.etapasProjetoGateways = etapasProjetoGateways;
    }

    public void deletar(UUID uuid) {
        etapasProjetoGateways.delete(uuid);
    }
}
