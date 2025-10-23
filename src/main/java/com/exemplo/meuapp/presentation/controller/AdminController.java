package com.exemplo.meuapp.presentation.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.meuapp.presentation.dto.AdminDashboardDTO;
import com.exemplo.meuapp.presentation.dto.AdminDashboardDTO.AtividadeRecenteDTO;
import com.exemplo.meuapp.presentation.dto.AdminDashboardDTO.EstatiticasGeraisDTO;
import com.exemplo.meuapp.presentation.dto.AdminDashboardDTO.UsuarioRecenteDTO;
import com.exemplo.meuapp.presentation.dto.LandingPageDTO;
import com.exemplo.meuapp.presentation.dto.LandingPageDTO.LandingPageResponseDTO;
import com.exemplo.meuapp.presentation.dto.LandingPageDTO.LandingPageStatsDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller para funcionalidades administrativas
 */
@RestController
@RequestMapping("/api/v1/admin")
@Tag(
    name = "👨‍💼 Administrador", 
    description = "Endpoints para gerenciamento administrativo do sistema"
)
public class AdminController {
    
    @GetMapping("/dashboard")
    @Operation(
        summary = "📊 Dashboard do Administrador",
        description = """
            **Retorna estatísticas e informações gerais do sistema para o dashboard administrativo.**
            
            **Funcionalidades:**
            - ✅ Estatísticas gerais do sistema
            - ✅ Atividades recentes
            - ✅ Usuários recém cadastrados
            - ✅ Indicadores de performance
            
            **Acesso:** Apenas usuários com tipo ADMIN
            """,
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "✅ Dashboard carregado com sucesso",
                content = @Content(
                    examples = @ExampleObject(
                        value = """
                        {
                          "estatisticas": {
                            "totalUsuarios": 150,
                            "totalAlunos": 120,
                            "totalProfessores": 25,
                            "totalVisitantes": 5,
                            "totalProjetos": 85,
                            "projetosPublicos": 60,
                            "projetosPrivados": 25,
                            "projetosEstaSemanaa": 12
                          },
                          "atividadesRecentes": [
                            {
                              "id": "1",
                              "tipo": "USUARIO_CADASTRADO",
                              "descricao": "Novo aluno cadastrado",
                              "usuarioEmail": "novo.aluno@senai.br",
                              "dataHora": "2025-01-12T10:30:00"
                            }
                          ],
                          "usuariosRecentes": [
                            {
                              "uuid": "123e4567-e89b-12d3-a456-426614174000",
                              "email": "novo.aluno@senai.br",
                              "nome": "João Silva",
                              "tipo": "ALUNO",
                              "criadoEm": "2025-01-12T10:30:00"
                            }
                          ]
                        }
                        """
                    )
                )
            ),
            @ApiResponse(responseCode = "403", description = "❌ Acesso negado - apenas administradores")
        }
    )
    public ResponseEntity<AdminDashboardDTO> getDashboard() {
        // Mock data para demonstração
        EstatiticasGeraisDTO stats = new EstatiticasGeraisDTO(
            150L, 120L, 25L, 5L, 85L, 60L, 25L, 12L
        );
        
        List<AtividadeRecenteDTO> atividades = List.of(
            new AtividadeRecenteDTO("1", "USUARIO_CADASTRADO", 
                "Novo aluno cadastrado", "novo.aluno@senai.br", LocalDateTime.now().minusHours(2)),
            new AtividadeRecenteDTO("2", "PROJETO_CRIADO", 
                "Novo projeto criado", "maria@senai.br", LocalDateTime.now().minusHours(5))
        );
        
        List<UsuarioRecenteDTO> usuariosRecentes = List.of(
            new UsuarioRecenteDTO(UUID.randomUUID().toString(), 
                "novo.aluno@senai.br", "João Silva", "ALUNO", LocalDateTime.now().minusHours(2))
        );
        
        AdminDashboardDTO dashboard = new AdminDashboardDTO(stats, atividades, usuariosRecentes);
        return ResponseEntity.ok(dashboard);
    }
    
    @GetMapping("/landing-page")
    @Operation(
        summary = "🎯 Obter configurações da Landing Page",
        description = """
            **Retorna as configurações atuais da landing page do sistema.**
            
            **Funcionalidades:**
            - ✅ Título e subtítulo da página
            - ✅ Imagem principal e descrição
            - ✅ Call-to-action configurável
            - ✅ Status de ativação/desativação
            
            **Uso:** Para carregar configurações na interface administrativa
            """
    )
    public ResponseEntity<LandingPageResponseDTO> getLandingPage() {
        // Mock data para demonstração
        LandingPageResponseDTO landingPage = new LandingPageResponseDTO(
            UUID.randomUUID().toString(),
            "Bem-vindo ao SENAI",
            "Plataforma de Projetos Acadêmicos",
            "Compartilhe seus projetos, colabore com outros alunos e atraia investimento de empresas",
            "/images/hero-senai.jpg",
            "/register",
            "Cadastre-se Agora",
            true,
            LocalDateTime.now().minusDays(30),
            LocalDateTime.now().minusDays(1),
            "admin@senai.br"
        );
        
        return ResponseEntity.ok(landingPage);
    }
    
    @PutMapping("/landing-page")
    @Operation(
        summary = "✏️ Atualizar configurações da Landing Page",
        description = """
            **Atualiza as configurações da landing page do sistema.**
            
            **Campos atualizáveis:**
            - `titulo`: Título principal da página
            - `subtitulo`: Subtítulo da página
            - `descricao`: Descrição/texto explicativo
            - `imagemPrincipal`: URL da imagem principal
            - `linkAcao`: Link do botão de ação
            - `textoAcao`: Texto do botão de ação
            - `ativo`: Se a landing page está ativa
            
            **Acesso:** Apenas usuários ADMIN
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "✅ Landing page atualizada com sucesso"
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "❌ Dados inválidos"
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "❌ Acesso negado - apenas administradores"
        )
    })
    public ResponseEntity<Map<String, Object>> updateLandingPage(
        @Parameter(
            description = "Novos dados da landing page",
            required = true,
            content = @Content(
                examples = @ExampleObject(
                    value = """
                    {
                      "titulo": "Bem-vindo ao SENAI 2025",
                      "subtitulo": "Inovação e Tecnologia",
                      "descricao": "A melhor plataforma para projetos acadêmicos",
                      "imagemPrincipal": "/images/nova-hero.jpg",
                      "linkAcao": "/cadastro",
                      "textoAcao": "Comece Agora",
                      "ativo": true
                    }
                    """
                )
            )
        )
        @RequestBody LandingPageDTO landingPageData
    ) {
        // Simulação de atualização
        return ResponseEntity.ok(Map.of(
            "message", "✅ Landing page atualizada com sucesso!",
            "id", UUID.randomUUID().toString(),
            "atualizadoEm", LocalDateTime.now(),
            "dados", Map.of(
                "titulo", landingPageData.titulo(),
                "subtitulo", landingPageData.subtitulo(),
                "descricao", landingPageData.descricao(),
                "ativo", landingPageData.ativo()
            )
        ));
    }
    
    @GetMapping("/landing-page/stats")
    @Operation(
        summary = "📈 Estatísticas da Landing Page",
        description = """
            **Retorna estatísticas de uso e performance da landing page.**
            
            **Métricas incluídas:**
            - Visitas totais e periódicas
            - Usuários cadastrados via landing page
            - Projetos públicos disponíveis
            - Taxa de conversão
            """
    )
    public ResponseEntity<LandingPageStatsDTO> getLandingPageStats() {
        LandingPageStatsDTO stats = new LandingPageStatsDTO(
            15420L, // totalVisitas
            89L,    // visitasHoje
            634L,   // visitasEstaSemana
            150L,   // totalUsuariosCadastrados
            120L,   // usuariosAtivos
            85L,    // totalProjetos
            60L     // projetosPublicos
        );
        
        return ResponseEntity.ok(stats);
    }
    
    @GetMapping("/users/summary")
    @Operation(
        summary = "👥 Resumo de Usuários",
        description = """
            **Retorna resumo estatístico dos usuários do sistema.**
            
            **Informações incluídas:**
            - Distribuição por tipo de usuário
            - Usuários ativos vs inativos
            - Cadastros por período
            - Atividade recente
            """
    )
    public ResponseEntity<Map<String, Object>> getUsersSummary() {
        return ResponseEntity.ok(Map.of(
            "total", 150,
            "distribuicao", Map.of(
                "ALUNO", 120,
                "PROFESSOR", 25,
                "VISITANTE", 5,
                "ADMIN", 3
            ),
            "status", Map.of(
                "ATIVO", 140,
                "INATIVO", 10
            ),
            "crescimentoSemanal", 12,
            "ultimaAtualizacao", LocalDateTime.now()
        ));
    }
    
    @PostMapping("/system/maintenance")
    @Operation(
        summary = "🔧 Iniciar Modo de Manutenção",
        description = """
            **Ativa o modo de manutenção do sistema.**
            
            **Funcionalidades:**
            - Bloqueia acesso de usuários não-admin
            - Exibe mensagem de manutenção
            - Permite configurar duração estimada
            
            **Acesso:** Apenas Super Admin
            """
    )
    public ResponseEntity<Map<String, Object>> startMaintenance(
        @RequestBody Map<String, Object> maintenanceData
    ) {
        return ResponseEntity.ok(Map.of(
            "message", "🔧 Modo de manutenção ativado",
            "iniciadoEm", LocalDateTime.now(),
            "duracaoEstimada", maintenanceData.getOrDefault("duracao", "2 horas"),
            "motivo", maintenanceData.getOrDefault("motivo", "Manutenção programada")
        ));
    }
}