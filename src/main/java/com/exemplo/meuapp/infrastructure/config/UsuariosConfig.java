package com.exemplo.meuapp.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exemplo.meuapp.application.port.in.usuarios.AtualizarUsuariosUseCase;
import com.exemplo.meuapp.application.port.in.usuarios.CriarUsuariosUseCase;
import com.exemplo.meuapp.application.port.in.usuarios.DeletarUsuariosUseCase;
import com.exemplo.meuapp.application.port.in.usuarios.EncontrarUsuariosUseCase;
import com.exemplo.meuapp.application.port.out.AlunosGateways;
import com.exemplo.meuapp.application.port.out.ProfessoresGateways;
import com.exemplo.meuapp.application.port.out.UsuariosGateways;

@Configuration
public class UsuariosConfig {    @Bean
    public CriarUsuariosUseCase criarUsuariosUseCase(UsuariosGateways usuariosGateways,
                                                    AlunosGateways alunosGateways,
                                                    ProfessoresGateways professoresGateways) {
        return new CriarUsuariosUseCase(usuariosGateways, alunosGateways, professoresGateways);
    }
    @Bean
    public EncontrarUsuariosUseCase encontrarUsuariosUseCase(UsuariosGateways usuariosGateways, 
                                                             AlunosGateways alunosGateways,
                                                             ProfessoresGateways professoresGateways) {
        return new EncontrarUsuariosUseCase(usuariosGateways, alunosGateways, professoresGateways);
    }

    @Bean
    public AtualizarUsuariosUseCase atualizarUsuariosUseCase(UsuariosGateways usuariosGateways) {
        return new AtualizarUsuariosUseCase(usuariosGateways);
    }

    @Bean
    public DeletarUsuariosUseCase deletarUsuariosUseCase(UsuariosGateways usuariosGateways) {
        return new DeletarUsuariosUseCase(usuariosGateways);
    }
}
