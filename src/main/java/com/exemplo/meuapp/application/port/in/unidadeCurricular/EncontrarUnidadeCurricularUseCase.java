package com.exemplo.meuapp.application.port.in.unidadeCurricular;

import com.exemplo.meuapp.application.port.out.UnidadeCurricularGateways;
import com.exemplo.meuapp.domain.model.UnidadeCurricular;

import java.util.List;
import java.util.UUID;

public class EncontrarUnidadeCurricularUseCase {

    private final UnidadeCurricularGateways unidadeCurricularGateways;

    public EncontrarUnidadeCurricularUseCase(UnidadeCurricularGateways unidadeCurricularGateways) {
        this.unidadeCurricularGateways = unidadeCurricularGateways;
    }

    public UnidadeCurricular buscarPorUUID(UUID unidadeCurricularId) {
        return unidadeCurricularGateways.getUnidadeCurricular(unidadeCurricularId);
    }

    public List<UnidadeCurricular> buscarTodos() {
        return unidadeCurricularGateways.getAllUnidadesCurriculares();
    }

    public List<UnidadeCurricular> buscarPorNome(String nome) {
        return unidadeCurricularGateways.getUnidadeCurricularByNome(nome);
    }

    public List<UnidadeCurricular> buscarPorCargaHoraria(String cargaHoraria) {
        return unidadeCurricularGateways.getUnidadeCurricularByCargaHoraria(cargaHoraria);
    }
}
