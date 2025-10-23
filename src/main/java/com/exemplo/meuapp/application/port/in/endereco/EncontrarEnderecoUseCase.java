package com.exemplo.meuapp.application.port.in.endereco;

import com.exemplo.meuapp.application.port.out.EnderecoGateways;
import com.exemplo.meuapp.domain.model.Endereco;

import java.util.List;
import java.util.UUID;

public class EncontrarEnderecoUseCase {
    private final EnderecoGateways enderecoGateways;

    public EncontrarEnderecoUseCase(EnderecoGateways enderecoGateways) {
        this.enderecoGateways = enderecoGateways;
    }

    public Endereco buscarPorUuid(UUID uuid) {
        return enderecoGateways.getEnderecoById(uuid);
    }

    public List<Endereco> buscarTodos() {
        return enderecoGateways.getAllEnderecos();
    }
}