package com.exemplo.meuapp.application.port.in.etapasProjeto;

import com.exemplo.meuapp.application.port.out.EtapasProjetoGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.EtapasProjeto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AtualizarEtapasProjetoUseCase {
    private final EtapasProjetoGateways etapasProjetoGateways;

    public AtualizarEtapasProjetoUseCase(EtapasProjetoGateways etapasProjetoGateways) {
        this.etapasProjetoGateways = etapasProjetoGateways;
    }

    public EtapasProjeto atualizar(UUID uuid, EtapasProjeto etapasProjeto) {
        EtapasProjeto etapasProjetoInDb = etapasProjetoGateways.getEtapaById(uuid);
        if (etapasProjetoInDb == null) {
            throw new RegraDeNegocioException("Etapa não encontrada.");
        }

        etapasProjeto.correct();

        if ((!etapasProjetoInDb.getNomeEtapa().equalsIgnoreCase(etapasProjeto.getNomeEtapa()) ||
             !etapasProjetoInDb.getProjeto().getUuid().equals(etapasProjeto.getProjeto().getUuid())) &&
            etapasProjetoGateways.existsByProjetoAndNomeEtapa(
                etapasProjeto.getProjeto().getUuid(),etapasProjeto.getNomeEtapa())) {
            throw new RegraDeNegocioException("Já existe uma etapa com este nome para o projeto.");
        }

        etapasProjetoInDb.setProjeto(etapasProjeto.getProjeto());
        etapasProjetoInDb.setNomeEtapa(etapasProjeto.getNomeEtapa());
        etapasProjetoInDb.setDescricao(etapasProjeto.getDescricao());
        etapasProjetoInDb.setOrdem(etapasProjeto.getOrdem());
        etapasProjetoInDb.setStatus(etapasProjeto.getStatus());
        etapasProjetoInDb.setAtualizadoEm(LocalDateTime.now());

        return etapasProjetoGateways.update(etapasProjetoInDb);
    }
}