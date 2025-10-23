package com.exemplo.meuapp.presentation.dto;

import java.time.LocalDateTime;

/**
 * DTO para gerenciamento de conteúdo da landing page
 */
public record LandingPageDTO(
    String titulo,
    String subtitulo, 
    String descricao,
    String imagemPrincipal,
    String linkAcao,
    String textoAcao,
    Boolean ativo
) {
    
    /**
     * DTO de resposta com informações completas da landing page
     */
    public record LandingPageResponseDTO(
        String id,
        String titulo,
        String subtitulo,
        String descricao, 
        String imagemPrincipal,
        String linkAcao,
        String textoAcao,
        Boolean ativo,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm,
        String atualizadoPor
    ) {}
    
    /**
     * DTO para estatísticas da landing page
     */
    public record LandingPageStatsDTO(
        Long totalVisitas,
        Long visitasHoje,
        Long visitasEstaSemana,
        Long totalUsuariosCadastrados,
        Long usuariosAtivos,
        Long totalProjetos,
        Long projetosPublicos
    ) {}
}