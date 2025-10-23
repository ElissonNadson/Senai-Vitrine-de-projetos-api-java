package com.exemplo.meuapp.infrastructure.config;

import com.exemplo.meuapp.application.port.in.anexoEtapa.AtualizarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.in.anexoEtapa.CriarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.in.anexoEtapa.DeletarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.in.anexoEtapa.EncontrarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.in.disciplina.AtualizarDisciplinaUseCase;
import com.exemplo.meuapp.application.port.in.disciplina.CriarDisciplinaUseCase;
import com.exemplo.meuapp.application.port.in.disciplina.DeleteDisciplinaUseCase;
import com.exemplo.meuapp.application.port.in.disciplina.EncontrarDisciplinaUseCase;
import com.exemplo.meuapp.application.port.out.AnexoEtapaGateways;
import com.exemplo.meuapp.application.port.out.DisciplinaGateways;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisciplinaConfig {

    @Bean
    public CriarDisciplinaUseCase criarDisciplinaUseCase(DisciplinaGateways disciplinaGateways) {
        return new CriarDisciplinaUseCase(disciplinaGateways);
    }

    @Bean
    public EncontrarDisciplinaUseCase encontrarDisciplinaUseCase(DisciplinaGateways disciplinaGateways) {
        return new EncontrarDisciplinaUseCase(disciplinaGateways);
    }

    @Bean
    public AtualizarDisciplinaUseCase atualizarDisciplinaUseCase(DisciplinaGateways disciplinaGateways) {
        return new AtualizarDisciplinaUseCase(disciplinaGateways);
    }

    @Bean
    public DeleteDisciplinaUseCase deletarDisciplinaUseCase (DisciplinaGateways disciplinaGateways) {
        return new DeleteDisciplinaUseCase(disciplinaGateways);
    }
}
