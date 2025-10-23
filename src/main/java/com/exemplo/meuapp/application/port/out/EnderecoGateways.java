package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.model.Endereco;

import java.util.List;
import java.util.UUID;

public interface EnderecoGateways {
     Endereco save(Endereco endereco);
     Endereco getEnderecoById(UUID enderecoId);
     void delete(UUID enderecoId);
     Endereco update(Endereco endereco);
     List<Endereco> getAllEnderecos();
}
