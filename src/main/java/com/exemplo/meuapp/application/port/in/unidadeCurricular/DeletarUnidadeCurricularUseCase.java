package com.exemplo.meuapp.application.port.in.unidadeCurricular;

import com.exemplo.meuapp.application.port.out.UnidadeCurricularGateways;
import java.util.UUID;

public class DeletarUnidadeCurricularUseCase {
    private UnidadeCurricularGateways unidadeCurricularGateways;

    public DeletarUnidadeCurricularUseCase(UnidadeCurricularGateways unidadeCurricularGateways) {
        this.unidadeCurricularGateways = unidadeCurricularGateways;
    }

    public void deletar(UUID uuid) {
        unidadeCurricularGateways.delete(uuid);
    }
}