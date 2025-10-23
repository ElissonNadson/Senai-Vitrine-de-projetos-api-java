package com.exemplo.meuapp.application.port.in.endereco;

import com.exemplo.meuapp.application.port.out.EnderecoGateways;
import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import com.exemplo.meuapp.domain.model.Endereco;

public class CriarEnderecoUseCase {
    private EnderecoGateways enderecoGateways;

    public CriarEnderecoUseCase(EnderecoGateways enderecoGateways) {
        this.enderecoGateways = enderecoGateways;
    }

    public Endereco criar(Endereco endereco) {
        endereco.correct();

        if (!endereco.getCep().matches("^\\d{5}-\\d{3}$")) {
            throw new DadosInvalidosException("CEP inválido. Formato esperado: 00000-000");
        }

        return enderecoGateways.save(endereco);
    }
}