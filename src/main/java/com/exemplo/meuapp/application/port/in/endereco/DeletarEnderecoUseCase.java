package com.exemplo.meuapp.application.port.in.endereco;

import com.exemplo.meuapp.application.port.out.EnderecoGateways;

import java.util.UUID;

public class DeletarEnderecoUseCase {
    private final EnderecoGateways enderecoGateways;

    public DeletarEnderecoUseCase(EnderecoGateways enderecoGateways) {
        this.enderecoGateways = enderecoGateways;
    }

    public void deletar(UUID id) {
        enderecoGateways.delete(id);
    }
}
