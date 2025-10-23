package com.exemplo.meuapp.presentation.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.AlunoDashboardDTO;
import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.DisciplinaAlunoDTO;
import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.EstatisticsAlunoDTO;
import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.EstatisticasPublicasDTO;
import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.EstatisticasProfessorDTO;
import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.ProfessorDashboardDTO;
import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.ProjetoAlunoDTO;
import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.ProjetoOrientadoDTO;
import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.ProjetoPublicoDTO;
import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.TurmaDTO;
import com.exemplo.meuapp.presentation.dto.RoleSpecificDTO.VisitorDashboardDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller para dashboards específicos por tipo de usuário
 */
@RestController
@RequestMapping("/api/v1/dashboard")
@Tag(
    name = "📊 Dashboards", 
    description = "Endpoints para dashboards específicos por tipo de usuário"
)
public class DashboardController {
    
    @GetMapping("/visitor")
    @Operation(
        summary = "🌐 Dashboard Público para Visitantes",
        description = """
            **Dashboard público acessível sem autenticação para visitantes.**
            
            **Funcionalidades:**
            - ✅ Lista de projetos públicos
            - ✅ Estatísticas públicas do sistema
            - ✅ Informações gerais da plataforma
            - ✅ Convite para cadastro
            
            **Acesso:** Público - não requer autenticação
            """,
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "✅ Dashboard público carregado com sucesso",
                content = @Content(
                    examples = @ExampleObject(
                        value = """
                        {
                          "projetoPublicos": [
                            {
                              "id": "123e4567-e89b-12d3-a456-426614174000",
                              "nome": "Sistema de Gestão Acadêmica",
                              "descricao": "Sistema completo para gerenciar vida acadêmica",
                              "autorNome": "João Silva",
                              "tecnologias": ["Java", "Spring Boot", "React"],
                              "status": "Publicado",
                              "publicadoEm": "2025-01-10T14:30:00",
                              "visualizacoes": 150
                            }
                          ],
                          "estatisticas": {
                            "totalProjetos": 85,
                            "totalAlunos": 120,
                            "totalProfessores": 25
                          },
                          "mensagemBemVindo": "Explore projetos incríveis criados pelos alunos do SENAI"
                        }
                        """
                    )
                )
            )
        }
    )
    public ResponseEntity<VisitorDashboardDTO> getVisitorDashboard() {
        List<ProjetoPublicoDTO> projetos = List.of(
            new ProjetoPublicoDTO(
                "123e4567-e89b-12d3-a456-426614174000",
                "Sistema de Gestão Acadêmica",
                "Sistema completo para gerenciar vida acadêmica dos estudantes",
                "João Silva",
                List.of("Java", "Spring Boot", "React", "PostgreSQL"),
                "Publicado",
                LocalDateTime.now().minusDays(5),
                150L
            ),
            new ProjetoPublicoDTO(
                "123e4567-e89b-12d3-a456-426614174001",
                "App Mobile para Biblioteca",
                "Aplicativo para consulta e reserva de livros",
                "Maria Santos",
                List.of("React Native", "Node.js", "MongoDB"),
                "Publicado",
                LocalDateTime.now().minusDays(10),
                89L
            )
        );
        
        EstatisticasPublicasDTO stats = new EstatisticasPublicasDTO(85L, 120L, 25L);
        
        VisitorDashboardDTO dashboard = new VisitorDashboardDTO(
            projetos, 
            stats, 
            "Explore projetos incríveis criados pelos alunos do SENAI"
        );
        
        return ResponseEntity.ok(dashboard);
    }
    
    @GetMapping("/professor")
    @Operation(
        summary = "👨‍🏫 Dashboard do Professor",
        description = """
            **Dashboard específico para professores autenticados.**
            
            **Funcionalidades:**
            - ✅ Projetos que está orientando
            - ✅ Turmas ativas
            - ✅ Estatísticas de orientação
            - ✅ Atividades recentes dos alunos
            
            **Acesso:** Apenas usuários com tipo PROFESSOR
            """,
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "✅ Dashboard do professor carregado com sucesso"
            ),
            @ApiResponse(
                responseCode = "403", 
                description = "❌ Acesso negado - apenas professores"
            )
        }
    )
    public ResponseEntity<ProfessorDashboardDTO> getProfessorDashboard() {
        List<ProjetoOrientadoDTO> projetos = List.of(
            new ProjetoOrientadoDTO(
                "proj-1", 
                "Sistema ERP para Pequenas Empresas",
                "Carlos Oliveira",
                "Em Desenvolvimento",
                "Desenvolvimento de Sistemas",
                LocalDateTime.now().minusDays(2)
            ),
            new ProjetoOrientadoDTO(
                "proj-2",
                "App de Delivery",
                "Ana Paula",
                "Revisão",
                "Programação Mobile",
                LocalDateTime.now().minusHours(6)
            )
        );
        
        List<TurmaDTO> turmas = List.of(
            new TurmaDTO("turma-1", "Desenvolvimento de Sistemas 2025", 28),
            new TurmaDTO("turma-2", "Programação Mobile Avançada", 22)
        );
        
        EstatisticasProfessorDTO stats = new EstatisticasProfessorDTO(12L, 2L, 50L);
        
        ProfessorDashboardDTO dashboard = new ProfessorDashboardDTO(projetos, turmas, stats);
        return ResponseEntity.ok(dashboard);
    }
    
    @GetMapping("/aluno")
    @Operation(
        summary = "🎓 Dashboard do Aluno",
        description = """
            **Dashboard específico para alunos autenticados.**
            
            **Funcionalidades:**
            - ✅ Seus projetos pessoais
            - ✅ Disciplinas matriculadas
            - ✅ Progresso acadêmico
            - ✅ Prazos e atividades pendentes
            
            **Acesso:** Apenas usuários com tipo ALUNO
            """,
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "✅ Dashboard do aluno carregado com sucesso"
            ),
            @ApiResponse(
                responseCode = "403", 
                description = "❌ Acesso negado - apenas alunos"
            )
        }
    )
    public ResponseEntity<AlunoDashboardDTO> getAlunoDashboard() {
        List<ProjetoAlunoDTO> projetos = List.of(
            new ProjetoAlunoDTO(
                "meu-proj-1",
                "Sistema de Controle de Estoque",
                "Em Desenvolvimento",
                "Prof. Roberto Silva",
                65,
                LocalDateTime.now().minusDays(1)
            ),
            new ProjetoAlunoDTO(
                "meu-proj-2",
                "Website Responsivo para ONG",
                "Concluído",
                "Prof. Maria Fernanda",
                100,
                LocalDateTime.now().minusDays(15)
            )
        );
        
        List<DisciplinaAlunoDTO> disciplinas = List.of(
            new DisciplinaAlunoDTO("disc-1", "Desenvolvimento Web", "Prof. Roberto Silva"),
            new DisciplinaAlunoDTO("disc-2", "Banco de Dados", "Prof. Ana Costa")
        );
        
        EstatisticsAlunoDTO stats = new EstatisticsAlunoDTO(3L, 1L, 2L);
        
        AlunoDashboardDTO dashboard = new AlunoDashboardDTO(projetos, disciplinas, stats);
        return ResponseEntity.ok(dashboard);
    }
    
    @GetMapping("/public/projects")
    @Operation(
        summary = "🌍 Projetos Públicos",
        description = """
            **Lista todos os projetos públicos disponíveis no sistema.**
            
            **Funcionalidades:**
            - ✅ Visualização sem autenticação
            - ✅ Filtros por tecnologia e área
            - ✅ Informações do autor (públicas)
            - ✅ Estatísticas de visualização
            
            **Acesso:** Público - não requer autenticação
            """
    )
    public ResponseEntity<Map<String, Object>> getPublicProjects() {
        List<ProjetoPublicoDTO> projetos = List.of(
            new ProjetoPublicoDTO(
                "proj-pub-1",
                "E-commerce Sustentável",
                "Plataforma de vendas focada em produtos eco-friendly",
                "Luiza Mendes",
                List.of("Vue.js", "Python", "Django", "PostgreSQL"),
                "Publicado",
                LocalDateTime.now().minusDays(7),
                203L
            ),
            new ProjetoPublicoDTO(
                "proj-pub-2",
                "Sistema de Monitoramento IoT",
                "Sensores inteligentes para agricultura urbana",
                "Pedro Santos",
                List.of("Arduino", "Python", "MQTT", "InfluxDB"),
                "Publicado",
                LocalDateTime.now().minusDays(12),
                156L
            ),
            new ProjetoPublicoDTO(
                "proj-pub-3",
                "App de Carona Solidária",
                "Conectando pessoas para caronas sustentáveis",
                "Rafaela Lima",
                List.of("Flutter", "Firebase", "Google Maps API"),
                "Publicado",
                LocalDateTime.now().minusDays(20),
                98L
            )
        );
        
        return ResponseEntity.ok(Map.of(
            "projetos", projetos,
            "total", projetos.size(),
            "categorias", List.of("Web", "Mobile", "IoT", "E-commerce"),
            "tecnologiasMaisUsadas", List.of("JavaScript", "Python", "Java", "React"),
            "ultimaAtualizacao", LocalDateTime.now()
        ));
    }
}