package com.exemplo.meuapp.infrastructure.config;

import com.exemplo.meuapp.application.port.in.professor.AtualizarProfessorUseCase;
import com.exemplo.meuapp.application.port.in.professor.CriarProfessorUseCase;
import com.exemplo.meuapp.application.port.in.professor.DeletarProfessorUseCase;
import com.exemplo.meuapp.application.port.in.professor.EncontrarProfessorUseCase;
import com.exemplo.meuapp.application.port.out.ProfessoresGateways;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfessorConfig {

    @Bean
    public CriarProfessorUseCase criarProfessorUseCase (ProfessoresGateways professoresGateways){
        return new CriarProfessorUseCase(professoresGateways);
    }

    @Bean
    public AtualizarProfessorUseCase atualizarProfessorUseCase (ProfessoresGateways professoresGateways){
        return new AtualizarProfessorUseCase(professoresGateways);
    }

    @Bean
    public DeletarProfessorUseCase deleteProfessorUseCase (ProfessoresGateways professoresGateways){
        return new DeletarProfessorUseCase(professoresGateways);
    }

    @Bean
    public EncontrarProfessorUseCase encontrarProfessorUseCase (ProfessoresGateways professoresGateways){
        return new EncontrarProfessorUseCase(professoresGateways);
    }
}
