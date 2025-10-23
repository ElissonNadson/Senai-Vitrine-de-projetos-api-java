package com.exemplo.meuapp.application.port.in.etapasProjeto;

import com.exemplo.meuapp.application.port.out.EtapasProjetoGateways;
import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.EtapasProjeto;

import java.time.LocalDateTime;

public class CriarEtapasProjetoUseCase {
    private EtapasProjetoGateways etapasProjetoGateways;

    public CriarEtapasProjetoUseCase(EtapasProjetoGateways etapasProjetoGateways) {
        this.etapasProjetoGateways = etapasProjetoGateways;
    }

    public EtapasProjeto criar(EtapasProjeto etapasProjeto) {
        etapasProjeto.correct();

        if (!etapasProjeto.getNomeEtapa().matches("^[\\w\\sáéíóúãõâêîôûçÁÉÍÓÚÃÕÂÊÎÔÛÇ-]+$")) {
            throw new DadosInvalidosException("Nome da etapa contém caracteres inválidos.");
        }

        if (etapasProjeto.getOrdem() < 1) {
            throw new DadosInvalidosException("A ordem da etapa deve ser um número positivo.");
        }

        if (etapasProjetoGateways.existsByProjetoAndNomeEtapa(
                etapasProjeto.getProjeto().getUuid(), etapasProjeto.getNomeEtapa())) {
            throw new RegraDeNegocioException("Já existe uma etapa com este nome para este projeto.");
        }
        if (etapasProjetoGateways.existsByProjetoAndOrdem(
                etapasProjeto.getProjeto().getUuid(), etapasProjeto.getOrdem())) {
            throw new RegraDeNegocioException("Já existe uma etapa com esta ordem para este projeto.");
        }


        if (etapasProjeto.getProjeto() != null
                && etapasProjeto.getProjeto().getStatus() != null
                && !etapasProjeto.getProjeto().getStatus().name().equals("ATIVO")) {
            throw new RegraDeNegocioException("Projeto associado está inativo.");
        }

        etapasProjeto.setCriadoEm(LocalDateTime.now());
        etapasProjeto.setAtualizadoEm(LocalDateTime.now());
        return etapasProjetoGateways.save(etapasProjeto);
    }
}