package com.exemplo.meuapp.presentation.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller de demonstração para o Swagger/OpenAPI
 * 
 * @author Time SENAI
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/demo")
@Tag(
    name = "🧪 Demonstração", 
    description = "Endpoints de demonstração para testar a API - não requerem autenticação"
)
public class DemoController {    @GetMapping("/health")
    @Operation(
        summary = "❤️ Health Check da API",
        description = """
            **Verifica se a API está funcionando corretamente.**
            
            **Use este endpoint para:**
            - Testar conectividade com a API
            - Verificar se o serviço está online
            - Obter informações básicas do sistema
            
            **Resposta:** Status da aplicação e links úteis
            """
    )
    @ApiResponse(
        responseCode = "200",
        description = "✅ API funcionando normalmente",
        content = @Content(
            examples = @ExampleObject(
                value = """
                {
                  "status": "UP",
                  "timestamp": "2025-06-19T00:47:30.123456",
                  "message": "🚀 API SENAI está funcionando!",
                  "swagger": "✅ Disponível em http://localhost:8080/swagger-ui.html"
                }
                """
            )
        )
    )
    public ResponseEntity<Map<String, Object>> healthCheck() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "timestamp", LocalDateTime.now(),
            "message", "🚀 API SENAI está funcionando!",
            "swagger", "✅ Disponível em http://localhost:8080/swagger-ui.html"
        ));
    }    @GetMapping("/projetos")
    @Operation(
        summary = "📋 Listar projetos de demonstração",
        description = """
            **Retorna uma lista de projetos fictícios para demonstração.**
            
            **Útil para:**
            - Testar a estrutura de resposta da API
            - Visualizar formato de dados de projetos
            - Demonstrar funcionalidades sem dados reais
            
            **Dados:** Lista com projetos de exemplo com diferentes status
            """
    )
    @ApiResponse(
        responseCode = "200",
        description = "✅ Lista de projetos retornada com sucesso",
        content = @Content(
            examples = @ExampleObject(
                value = """
                [
                  {
                    "id": "123e4567-e89b-12d3-a456-426614174000",
                    "nome": "Sistema de Gestão Acadêmica",
                    "autor": "João Silva",
                    "tecnologias": ["Java", "Spring Boot", "PostgreSQL"],
                    "status": "Publicado"
                  },
                  {
                    "id": "123e4567-e89b-12d3-a456-426614174001",
                    "nome": "App Mobile para Biblioteca",
                    "autor": "Maria Santos",
                    "tecnologias": ["React Native", "Node.js", "MongoDB"],
                    "status": "Em Desenvolvimento"
                  }
                ]
                """
            )
        )
    )
    public ResponseEntity<List<Map<String, Object>>> listarProjetos() {
        List<Map<String, Object>> projetos = List.of(
            Map.of(
                "id", UUID.randomUUID(),
                "nome", "Sistema de Gestão Acadêmica",
                "autor", "João Silva",
                "tecnologias", List.of("Java", "Spring Boot", "PostgreSQL"),
                "status", "Publicado"
            ),
            Map.of(
                "id", UUID.randomUUID(),
                "nome", "App Mobile para Biblioteca", 
                "autor", "Maria Santos",
                "tecnologias", List.of("React Native", "Node.js", "MongoDB"),
                "status", "Em Desenvolvimento"
            )
        );
        
        return ResponseEntity.ok(projetos);
    }    @PostMapping("/projetos")
    @Operation(
        summary = "➕ Criar projeto de demonstração",
        description = """
            **Simula a criação de um novo projeto no sistema.**
            
            **Como usar:**
            - Envie apenas o nome do projeto
            - O sistema gerará automaticamente: ID, autor, status e data
            
            **Resposta:** Dados do projeto criado com informações geradas
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "✅ Projeto criado com sucesso",
            content = @Content(
                examples = @ExampleObject(
                    value = """
                    {
                      "id": "123e4567-e89b-12d3-a456-426614174000",
                      "nome": "Meu Projeto Teste",
                      "autor": "Usuário Atual",
                      "status": "Rascunho",
                      "criadoEm": "2025-06-19T00:47:30.123456",
                      "message": "✅ Projeto criado com sucesso!"
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<Map<String, Object>> criarProjeto(
        @Parameter(
            description = "Dados do projeto a ser criado",
            required = true,
            content = @Content(
                examples = @ExampleObject(
                    name = "Exemplo de projeto",
                    value = """
                    {
                      "nome": "Meu Projeto Incrível"
                    }
                    """
                )
            )
        )
        @RequestBody Map<String, Object> projeto
    ) {
        Map<String, Object> novoProjeto = Map.of(
            "id", UUID.randomUUID(),
            "nome", projeto.get("nome"),
            "autor", "Usuário Atual",
            "status", "Rascunho",
            "criadoEm", LocalDateTime.now(),
            "message", "✅ Projeto criado com sucesso!"
        );
        
        return ResponseEntity.status(201).body(novoProjeto);
    }

    @GetMapping("/projetos/{id}")
    public ResponseEntity<Map<String, Object>> buscarProjeto(@PathVariable UUID id) {
        Map<String, Object> projeto = Map.of(
            "id", id,
            "nome", "Sistema de Gestão Acadêmica",
            "descricao", "Projeto para gerenciar matrículas, notas e frequência",
            "autor", "João Silva",
            "tecnologias", List.of("Java 21", "Spring Boot 3", "PostgreSQL", "React"),
            "status", "Publicado",
            "criadoEm", LocalDateTime.now().minusDays(30),
            "atualizadoEm", LocalDateTime.now().minusDays(2)
        );
          return ResponseEntity.ok(projeto);
    }
}
