package com.exemplo.meuapp.infrastructure.config;

import com.exemplo.meuapp.application.port.in.unidadeCurricular.AtualizarUnidadeCurricularUseCase;
import com.exemplo.meuapp.application.port.in.unidadeCurricular.CriarUnidadeCurricularUseCase;
import com.exemplo.meuapp.application.port.in.unidadeCurricular.DeletarUnidadeCurricularUseCase;
import com.exemplo.meuapp.application.port.in.unidadeCurricular.EncontrarUnidadeCurricularUseCase;
import com.exemplo.meuapp.application.port.out.UnidadeCurricularGateways;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnidadeCurricularConfig {

    @Bean
    public CriarUnidadeCurricularUseCase criarUnidadeCurricularUseCase(UnidadeCurricularGateways unidadeCurricularGateways) {
        return new CriarUnidadeCurricularUseCase(unidadeCurricularGateways);
    }

    @Bean
    public AtualizarUnidadeCurricularUseCase atualizarUnidadeCurricularUseCase(UnidadeCurricularGateways unidadeCurricularGateways) {
        return new AtualizarUnidadeCurricularUseCase(unidadeCurricularGateways);
    }

    @Bean
    public DeletarUnidadeCurricularUseCase deleteUnidadeCurricularUseCase(UnidadeCurricularGateways unidadeCurricularGateways) {
        return new DeletarUnidadeCurricularUseCase(unidadeCurricularGateways);
    }

    @Bean
    public EncontrarUnidadeCurricularUseCase encontrarUnidadeCurricularUseCase(UnidadeCurricularGateways unidadeCurricularGateways) {
        return new EncontrarUnidadeCurricularUseCase(unidadeCurricularGateways);
    }
}
