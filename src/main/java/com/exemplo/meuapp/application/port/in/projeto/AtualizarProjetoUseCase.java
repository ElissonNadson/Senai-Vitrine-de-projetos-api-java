package com.exemplo.meuapp.application.port.in.projeto;

import com.exemplo.meuapp.application.port.out.ProjetoGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.Projeto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AtualizarProjetoUseCase {
    private final ProjetoGateways projetoGateways;

    public AtualizarProjetoUseCase(ProjetoGateways projetoGateways) {
        this.projetoGateways = projetoGateways;
    }

    public Projeto atualizar(UUID uuid, Projeto projeto) {
        Projeto projetoInDb = projetoGateways.getProjetoById(uuid);
        if (projetoInDb == null) {
            throw new RegraDeNegocioException("Projeto não encontrado.");
        }

        projeto.correct();

        if (!projetoInDb.getTitulo().equalsIgnoreCase(projeto.getTitulo()) &&
                projetoGateways.existsByTitulo(projeto.getTitulo())) {
            throw new RegraDeNegocioException("Já existe um projeto com este título.");
        }

        if (projeto.getCodigo() != null &&
                !projeto.getCodigo().isBlank() &&
                !projetoInDb.getCodigo().equalsIgnoreCase(projeto.getCodigo()) &&
                projetoGateways.existsByCodigo(projeto.getCodigo())) {
            throw new RegraDeNegocioException("Já existe um projeto com este código.");
        }

        projetoInDb.setTitulo(projeto.getTitulo());
        projetoInDb.setDescricao(projeto.getDescricao());
        projetoInDb.setCurso(projeto.getCurso());
        projetoInDb.setTurma(projeto.getTurma());
        projetoInDb.setLabMaker(projeto.isLabMaker());
        projetoInDb.setParticipouSaga(projeto.isParticipouSaga());
        projetoInDb.setItinerario(projeto.isItinerario());
        projetoInDb.setUnidadeCurricular(projeto.getUnidadeCurricular());
        projetoInDb.setLiderProjeto(projeto.getLiderProjeto());
        projetoInDb.setBannerUrl(projeto.getBannerUrl());
        projetoInDb.setCodigo(projeto.getCodigo());
        projetoInDb.setVisibilidadeCodigo(projeto.getVisibilidadeCodigo());
        projetoInDb.setVisibilidadeAnexos(projeto.getVisibilidadeAnexos());
        projetoInDb.setStatus(projeto.getStatus());
        projetoInDb.setAtualizadoEm(LocalDateTime.now());
        return projetoGateways.update(projetoInDb);
    }
}