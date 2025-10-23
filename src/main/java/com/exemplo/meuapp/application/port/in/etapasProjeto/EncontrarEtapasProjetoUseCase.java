package com.exemplo.meuapp.application.port.in.etapasProjeto;

import com.exemplo.meuapp.application.port.out.EtapasProjetoGateways;
import com.exemplo.meuapp.domain.model.EtapasProjeto;

import java.util.List;
import java.util.UUID;

public class EncontrarEtapasProjetoUseCase {
    private final EtapasProjetoGateways etapasProjetoGateways;

    public EncontrarEtapasProjetoUseCase(EtapasProjetoGateways etapasProjetoGateways) {
        this.etapasProjetoGateways = etapasProjetoGateways;
    }

    public EtapasProjeto buscarPorUuid(UUID uuid) {
        return etapasProjetoGateways.getEtapaById(uuid);
    }

    public List<EtapasProjeto> buscarTodas() {
        return etapasProjetoGateways.getAllEtapas();
    }

    public List<EtapasProjeto> buscarPorProjeto(UUID projetoId) {
        return etapasProjetoGateways.getEtapasByProjetoId(projetoId);
    }

    public List<EtapasProjeto> buscarPorStatus(String status) {
        return etapasProjetoGateways.getEtapasByStatus(status);
    }
}