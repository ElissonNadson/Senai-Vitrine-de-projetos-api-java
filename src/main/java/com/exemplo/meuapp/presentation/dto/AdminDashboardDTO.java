package com.exemplo.meuapp.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para dashboard do administrador
 */
public record AdminDashboardDTO(
    EstatiticasGeraisDTO estatisticas,
    List<AtividadeRecenteDTO> atividadesRecentes,
    List<UsuarioRecenteDTO> usuariosRecentes
) {
    
    /**
     * Estatísticas gerais do sistema
     */
    public record EstatiticasGeraisDTO(
        Long totalUsuarios,
        Long totalAlunos,
        Long totalProfessores,
        Long totalVisitantes,
        Long totalProjetos,
        Long projetosPublicos,
        Long projetosPrivados,
        Long projetosEstaSemana
    ) {}
    
    /**
     * Atividade recente no sistema
     */
    public record AtividadeRecenteDTO(
        String id,
        String tipo, // "USUARIO_CADASTRADO", "PROJETO_CRIADO", "LOGIN", etc.
        String descricao,
        String usuarioEmail,
        LocalDateTime dataHora
    ) {}
    
    /**
     * Usuários recém cadastrados
     */
    public record UsuarioRecenteDTO(
        String uuid,
        String email,
        String nome,
        String tipo,
        LocalDateTime criadoEm
    ) {}
}