package com.exemplo.meuapp.application.port.in.unidadeCurricular;

import com.exemplo.meuapp.application.port.out.UnidadeCurricularGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.UnidadeCurricular;

import java.time.LocalDateTime;

public class CriarUnidadeCurricularUseCase {
    private UnidadeCurricularGateways unidadeCurricularGateways;

    public CriarUnidadeCurricularUseCase(UnidadeCurricularGateways unidadeCurricularGateways) {
        this.unidadeCurricularGateways = unidadeCurricularGateways;
    }

    public UnidadeCurricular criar(UnidadeCurricular unidadeCurricular) {
        unidadeCurricular.correct();

        if (unidadeCurricularGateways.existsByNome(unidadeCurricular.getNome())) {
            throw new RegraDeNegocioException("Já existe uma unidade curricular com este nome.");
        }

        unidadeCurricular.setCriadoEm(LocalDateTime.now());
        unidadeCurricular.setAtualizadoEm(LocalDateTime.now());
        return unidadeCurricularGateways.save(unidadeCurricular);
    }
}