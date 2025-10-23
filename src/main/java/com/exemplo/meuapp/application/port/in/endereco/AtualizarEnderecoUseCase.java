package com.exemplo.meuapp.application.port.in.endereco;

import com.exemplo.meuapp.application.port.out.EnderecoGateways;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.Endereco;

import java.util.UUID;

public class AtualizarEnderecoUseCase {
    private final EnderecoGateways enderecoGateways;

    public AtualizarEnderecoUseCase(EnderecoGateways enderecoGateways) {
        this.enderecoGateways = enderecoGateways;
    }

    public Endereco atualizar(UUID uuid, Endereco endereco) {
        Endereco enderecoInDb = enderecoGateways.getEnderecoById(uuid);

        if (enderecoInDb == null) {
            throw new RegraDeNegocioException("Endereço não encontrado.");
        }

        endereco.correct();

        enderecoInDb.setCep(endereco.getCep());
        enderecoInDb.setLogradouro(endereco.getLogradouro());
        enderecoInDb.setNumero(endereco.getNumero());
        enderecoInDb.setComplemento(endereco.getComplemento());
        enderecoInDb.setBairro(endereco.getBairro());
        enderecoInDb.setCidade(endereco.getCidade());
        enderecoInDb.setEstado(endereco.getEstado());
        enderecoInDb.setPais(endereco.getPais());

        return enderecoGateways.update(enderecoInDb);
    }
}