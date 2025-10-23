package com.exemplo.meuapp.application.port.in.unidadeCurricular;

import com.exemplo.meuapp.application.port.out.UnidadeCurricularGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.UnidadeCurricular;

import java.time.LocalDateTime;
import java.util.UUID;

public class AtualizarUnidadeCurricularUseCase {
    private final UnidadeCurricularGateways unidadeCurricularGateways;

    public AtualizarUnidadeCurricularUseCase(UnidadeCurricularGateways unidadeCurricularGateways) {
        this.unidadeCurricularGateways = unidadeCurricularGateways;
    }

    public UnidadeCurricular atualizar(UUID uuid, UnidadeCurricular unidadeCurricular) {
        UnidadeCurricular unidadeCurricularInDb = unidadeCurricularGateways.getUnidadeCurricular(uuid);
        if (unidadeCurricularInDb == null) {
            throw new RegraDeNegocioException("Unidade Curricular não encontrada.");
        }

        unidadeCurricular.correct();


        if (!unidadeCurricularInDb.getNome().equalsIgnoreCase(unidadeCurricular.getNome()) &&
                unidadeCurricularGateways.existsByNome(unidadeCurricular.getNome())) {
            throw new RegraDeNegocioException("Já existe uma unidade curricular com este nome.");
        }

        unidadeCurricularInDb.setNome(unidadeCurricular.getNome());
        unidadeCurricularInDb.setDescricao(unidadeCurricular.getDescricao());
        unidadeCurricularInDb.setCargaHoraria(unidadeCurricular.getCargaHoraria());
        unidadeCurricularInDb.setAtualizadoEm(LocalDateTime.now());
        return unidadeCurricularGateways.update(unidadeCurricularInDb);
    }
}