package com.exemplo.meuapp.application.port.in.anexoEtapa;

import com.exemplo.meuapp.application.port.out.AnexoEtapaGateways;

import java.util.UUID;

public class DeletarAnexoEtapaUseCase {
    private final AnexoEtapaGateways anexoEtapaGateways;

    public DeletarAnexoEtapaUseCase(AnexoEtapaGateways anexoEtapaGateways) {
        this.anexoEtapaGateways = anexoEtapaGateways;
    }

    public void deletar(UUID anexoUUID) {
        anexoEtapaGateways.delete(anexoUUID);
    }
}
