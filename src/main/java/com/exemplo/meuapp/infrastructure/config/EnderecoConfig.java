package com.exemplo.meuapp.infrastructure.config;

import com.exemplo.meuapp.application.port.in.endereco.AtualizarEnderecoUseCase;
import com.exemplo.meuapp.application.port.in.endereco.CriarEnderecoUseCase;
import com.exemplo.meuapp.application.port.in.endereco.DeletarEnderecoUseCase;
import com.exemplo.meuapp.application.port.in.endereco.EncontrarEnderecoUseCase;
import com.exemplo.meuapp.application.port.out.EnderecoGateways;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoConfig {

    @Bean
    public CriarEnderecoUseCase criarEnderecoUseCase (EnderecoGateways enderecoGateways) {
        return new CriarEnderecoUseCase(enderecoGateways);
    }

    @Bean
    public AtualizarEnderecoUseCase atualizarEnderecoUseCase (EnderecoGateways enderecoGateways) {
        return new AtualizarEnderecoUseCase(enderecoGateways);
    }

    @Bean
    public DeletarEnderecoUseCase deleteEnderecoUseCase (EnderecoGateways enderecoGateways) {
        return new DeletarEnderecoUseCase(enderecoGateways);
    }

    @Bean
    public EncontrarEnderecoUseCase encontrarEnderecoUseCase (EnderecoGateways enderecoGateways) {
        return new EncontrarEnderecoUseCase(enderecoGateways);
    }
}
