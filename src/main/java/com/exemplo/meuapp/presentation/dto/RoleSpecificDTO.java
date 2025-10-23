package com.exemplo.meuapp.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTOs para funcionalidades específicas por tipo de usuário
 */
public class RoleSpecificDTO {
    
    /**
     * DTO para dashboard do visitante (público)
     */
    public record VisitorDashboardDTO(
        List<ProjetoPublicoDTO> projetoPublicos,
        EstatisticasPublicasDTO estatisticas,
        String mensagemBemVindo
    ) {}
    
    /**
     * DTO para projetos públicos visíveis para visitantes
     */
    public record ProjetoPublicoDTO(
        String id,
        String nome,
        String descricao,
        String autorNome,
        List<String> tecnologias,
        String status,
        LocalDateTime publicadoEm,
        Long visualizacoes
    ) {}
    
    /**
     * DTO para dashboard do professor
     */
    public record ProfessorDashboardDTO(
        List<ProjetoOrientadoDTO> projetosOrientados,
        List<TurmaDTO> turmasAtivas,
        EstatisticasProfessorDTO estatisticas
    ) {}
    
    /**
     * DTO para projetos orientados pelo professor
     */
    public record ProjetoOrientadoDTO(
        String id,
        String nome,
        String alunoNome,
        String status,
        String disciplina,
        LocalDateTime ultimaAtualizacao
    ) {}
    
    /**
     * DTO para dashboard do aluno
     */
    public record AlunoDashboardDTO(
        List<ProjetoAlunoDTO> meusProjetos,
        List<DisciplinaAlunoDTO> minhasDisciplinas,
        EstatisticsAlunoDTO estatisticas
    ) {}
    
    /**
     * DTO para projetos do aluno
     */
    public record ProjetoAlunoDTO(
        String id,
        String nome,
        String status,
        String orientadorNome,
        Integer progresso,
        LocalDateTime ultimaAtualizacao
    ) {}
    
    /**
     * Estatísticas específicas por tipo de usuário
     */
    public record EstatisticasPublicasDTO(
        Long totalProjetos,
        Long totalAlunos,
        Long totalProfessores
    ) {}
    
    public record EstatisticasProfessorDTO(
        Long totalProjetosOrientados,
        Long totalTurmas,
        Long totalAlunos
    ) {}
    
    public record EstatisticsAlunoDTO(
        Long totalProjetos,
        Long projetosConcluidos,
        Long projetosEmAndamento
    ) {}
    
    public record TurmaDTO(
        String id,
        String nome,
        Integer totalAlunos
    ) {}
    
    public record DisciplinaAlunoDTO(
        String id,
        String nome,
        String professorNome
    ) {}
}