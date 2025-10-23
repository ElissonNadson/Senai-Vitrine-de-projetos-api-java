package com.exemplo.meuapp.infrastructure.config;

import com.exemplo.meuapp.application.port.in.anexoEtapa.AtualizarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.in.anexoEtapa.CriarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.in.anexoEtapa.DeletarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.in.anexoEtapa.EncontrarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.out.AnexoEtapaGateways;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnexoEtapaConfig {

    @Bean
    public CriarAnexoEtapaUseCase criarAnexoEtapaUseCase(AnexoEtapaGateways anexoEtapaGateways) {
        return new CriarAnexoEtapaUseCase(anexoEtapaGateways);
    }

    @Bean
    public EncontrarAnexoEtapaUseCase encontrarAnexoEtapaUseCase(AnexoEtapaGateways anexoEtapaGateways) {
        return new EncontrarAnexoEtapaUseCase(anexoEtapaGateways);
    }

    @Bean
    public AtualizarAnexoEtapaUseCase atualizarAnexoEtapaUseCase(AnexoEtapaGateways anexoEtapaGateways) {
        return new AtualizarAnexoEtapaUseCase(anexoEtapaGateways);
    }

    @Bean
    public DeletarAnexoEtapaUseCase deletarAnexoEtapaUseCase (AnexoEtapaGateways anexoEtapaGateways) {
        return new DeletarAnexoEtapaUseCase(anexoEtapaGateways);
    }
}
