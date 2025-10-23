package com.exemplo.meuapp.infrastructure.config;

import com.exemplo.meuapp.application.port.in.disciplinaProjeto.AtualizarDisciplinaProjetoUseCase;
import com.exemplo.meuapp.application.port.in.disciplinaProjeto.CriarDisciplinaProjetoUseCase;
import com.exemplo.meuapp.application.port.in.disciplinaProjeto.DeletarDisciplinaProjetoUseCase;
import com.exemplo.meuapp.application.port.in.disciplinaProjeto.EncontrarDisciplinaProjetoUseCase;
import com.exemplo.meuapp.application.port.out.DisciplinaProjetoGateways;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisciplinaProjetoConfig {

    @Bean
    public CriarDisciplinaProjetoUseCase criarDisciplinaProjetoUseCase(DisciplinaProjetoGateways disciplinaProjetoGateways) {
        return new CriarDisciplinaProjetoUseCase(disciplinaProjetoGateways);
    }

    @Bean
    public EncontrarDisciplinaProjetoUseCase encontrarDisciplinaProjetoUseCase(DisciplinaProjetoGateways disciplinaProjetoGateways) {
        return new EncontrarDisciplinaProjetoUseCase(disciplinaProjetoGateways);
    }

    @Bean
    public AtualizarDisciplinaProjetoUseCase atualizarDisciplinaProjetoUseCase(DisciplinaProjetoGateways disciplinaProjetoGateways) {
        return new AtualizarDisciplinaProjetoUseCase(disciplinaProjetoGateways);
    }

    @Bean
    public DeletarDisciplinaProjetoUseCase deletarDisciplinaProjetoUseCase(DisciplinaProjetoGateways disciplinaProjetoGateways) {
        return new DeletarDisciplinaProjetoUseCase(disciplinaProjetoGateways);
    }
}