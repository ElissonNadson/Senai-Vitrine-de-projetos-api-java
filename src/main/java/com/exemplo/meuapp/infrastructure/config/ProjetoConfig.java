package com.exemplo.meuapp.infrastructure.config;

import com.exemplo.meuapp.application.port.in.projeto.AtualizarProjetoUseCase;
import com.exemplo.meuapp.application.port.in.projeto.CriarProjetoUseCase;
import com.exemplo.meuapp.application.port.in.projeto.DeletarProjetoUseCase;
import com.exemplo.meuapp.application.port.in.projeto.EncontrarProjetoUseCase;
import com.exemplo.meuapp.application.port.out.ProjetoGateways;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjetoConfig {

    @Bean
    public CriarProjetoUseCase criarProjetoUseCase (ProjetoGateways projetoGateways) {
        return new CriarProjetoUseCase(projetoGateways);
    }

    @Bean
    public AtualizarProjetoUseCase atualizarProjetoUseCase (ProjetoGateways projetoGateways) {
        return new AtualizarProjetoUseCase(projetoGateways);
    }

    @Bean
    public DeletarProjetoUseCase deleteProjetoUseCase (ProjetoGateways projetoGateways) {
        return new DeletarProjetoUseCase(projetoGateways);
    }

    @Bean
    public EncontrarProjetoUseCase encontrarProjetoUseCase (ProjetoGateways projetoGateways) {
        return new EncontrarProjetoUseCase(projetoGateways);
    }
}
